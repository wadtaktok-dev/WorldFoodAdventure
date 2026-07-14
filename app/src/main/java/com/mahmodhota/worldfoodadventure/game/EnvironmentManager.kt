package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.mutableStateListOf
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

data class SkyObject(val id: String, var x: Float, var y: Float, val speed: Float, val emoji: String, val type: String, val scale: Float = 1f, val alpha: Float = 1f)

class EnvironmentManager {
    val skyObjects = mutableStateListOf<SkyObject>()
    private var skySpawnTimer = 0f
    private var nextSpawnDelay = 10f

    fun update(dt: Float, screenWidth: Float, screenHeight: Float) {
        if (screenWidth <= 0 || screenHeight <= 0) return
        val clampedDt = dt.coerceAtMost(0.05f)

        // Update movement
        try {
            for (i in skyObjects.indices.reversed()) {
                val obj = skyObjects[i]
                
                if (!obj.x.isFinite() || !obj.y.isFinite()) {
                    skyObjects.removeAt(i)
                    continue
                }

                obj.x += obj.speed * clampedDt
                
                // Special movements
                when(obj.type) {
                    "shooting_star" -> {
                        obj.y += obj.speed * 0.4f * clampedDt
                    }
                    "bird" -> {
                        obj.y += sin(obj.x * 0.02f + obj.id.hashCode()) * 25f * clampedDt
                    }
                    "butterfly" -> {
                        obj.y += cos(obj.x * 0.05f + obj.id.hashCode()) * 35f * clampedDt
                    }
                    "cloud_v2" -> {
                        // Slow drift
                    }
                }
                
                // Bounds removal
                if (obj.x > screenWidth + 600 || obj.y > screenHeight + 600 || obj.y < -600 || obj.x < -600) {
                    skyObjects.removeAt(i)
                }
            }
        } catch (e: Exception) {
            android.util.Log.e("Environment", "Error updating sky objects", e)
        }

        // Spawning logic
        skySpawnTimer += clampedDt
        if (skySpawnTimer >= nextSpawnDelay) {
            if (skyObjects.size < 3) {
                spawnSkyObject(screenWidth, screenHeight)
                skySpawnTimer = 0f
                nextSpawnDelay = 15f + Random.nextFloat() * 25f // Calmer world
            }
        }
    }

    private fun spawnSkyObject(screenWidth: Float, screenHeight: Float) {
        // Rarities
        val rand = Random.nextFloat()
        val type = when {
            rand < 0.5f -> "cloud_v2"
            rand < 0.7f -> "bird"
            rand < 0.85f -> "star"
            rand < 0.92f -> "plane"
            rand < 0.97f -> "balloon"
            else -> "shooting_star"
        }
        
        val emoji = when(type) {
            "plane" -> "✈️"
            "balloon" -> "🎈"
            "bird" -> listOf("🕊️", "🦅", "🐦").random()
            "star" -> "⭐"
            "cloud_v2" -> listOf("☁️", "🌥️").random()
            "shooting_star" -> "🌠"
            else -> "☁️"
        }

        val speed = when(type) {
            "plane" -> 160f
            "balloon" -> 18f
            "bird" -> 80f + Random.nextFloat() * 40f
            "star" -> 3f
            "cloud_v2" -> 6f + Random.nextFloat() * 12f
            "shooting_star" -> 1500f
            else -> 12f
        }

        val scale = when(type) {
            "cloud_v2" -> 1.8f + Random.nextFloat() * 2.8f
            "star" -> 0.3f + Random.nextFloat() * 0.5f
            else -> 1.0f + Random.nextFloat() * 0.4f
        }

        skyObjects.add(SkyObject(
            id = Random.nextInt().toString(),
            x = if (type == "shooting_star") Random.nextFloat() * screenWidth else -550f,
            y = Random.nextFloat() * (screenHeight * 0.45f),
            speed = speed,
            emoji = emoji,
            type = type,
            scale = scale,
            alpha = if (type == "star") 0.3f + Random.nextFloat() * 0.6f else 0.8f + Random.nextFloat() * 0.2f
        ))
    }
}
