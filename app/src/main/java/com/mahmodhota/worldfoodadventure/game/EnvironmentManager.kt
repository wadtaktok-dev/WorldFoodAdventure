package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.mutableStateListOf
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

data class SkyObject(val id: String, var x: Float, var y: Float, val speed: Float, val emoji: String, val type: String, val scale: Float = 1f, val alpha: Float = 1f)

class EnvironmentManager {
    val skyObjects = mutableStateListOf<SkyObject>()
    private val spawnChance = 0.008f // Increased for Premium 2.0

    fun update(dt: Float, screenWidth: Float) {
        if (screenWidth <= 0) return

        try {
            for (i in skyObjects.indices.reversed()) {
                val obj = skyObjects[i]
                
                // Validate coordinates
                if (!obj.x.isFinite() || !obj.y.isFinite()) {
                    skyObjects.removeAt(i)
                    continue
                }

                obj.x += obj.speed * dt
                
                // Premium Movement
                if (obj.type == "shooting_star") {
                    obj.y += obj.speed * 0.4f * dt
                } else if (obj.type == "bird") {
                    obj.y += sin(obj.x * 0.02f) * 20f * dt // Wavy flight
                } else if (obj.type == "butterfly") {
                    obj.y += cos(obj.x * 0.05f) * 40f * dt // Erratic flight
                }
                
                if (obj.x > screenWidth + 500 || obj.y > 2000 || obj.y < -500 || obj.x < -500) {
                    skyObjects.removeAt(i)
                }
            }
        } catch (e: Exception) {
            android.util.Log.e("Environment", "Error updating sky objects", e)
        }

        if (Random.nextFloat() < spawnChance && skyObjects.size < 8) {
            val type = listOf("plane", "balloon", "bird", "bird", "butterfly", "butterfly", "star", "cloud_v2", "cloud_v2", "shooting_star", "cloud_v3").random()
            val emoji = when(type) {
                "plane" -> "✈️"
                "balloon" -> "🎈"
                "bird" -> listOf("🕊️", "🦅", "🐦", "🦆").random()
                "butterfly" -> listOf("🦋", "✨", "🧚").random()
                "star" -> "⭐"
                "cloud_v2" -> listOf("☁️", "🌥️", "🌤️", "🌫️").random()
                "cloud_v3" -> listOf("☁️", "💨").random()
                "shooting_star" -> "🌠"
                else -> "☁️"
            }
            val speed = when(type) {
                "plane" -> 140f
                "balloon" -> 12f
                "bird" -> 60f + Random.nextFloat() * 40f
                "butterfly" -> 30f + Random.nextFloat() * 20f
                "star" -> 1.5f
                "cloud_v2" -> 3f + Random.nextFloat() * 6f 
                "cloud_v3" -> 1.5f + Random.nextFloat() * 3f
                "shooting_star" -> 1000f
                else -> 8f
            }
            val scale = when(type) {
                "cloud_v2", "cloud_v3" -> 1.5f + Random.nextFloat() * 3.5f
                "star" -> 0.3f + Random.nextFloat() * 0.5f
                "bird" -> 0.7f + Random.nextFloat() * 0.4f
                else -> 0.9f + Random.nextFloat() * 0.3f
            }
            
            skyObjects.add(SkyObject(
                id = Random.nextInt().toString(),
                x = if (type == "shooting_star") Random.nextFloat() * screenWidth else -450f,
                y = if (type == "shooting_star") -200f else Random.nextFloat() * 450f,
                speed = speed,
                emoji = emoji,
                type = type,
                scale = scale,
                alpha = if (type == "star") 0.2f + Random.nextFloat() * 0.6f else 0.7f + Random.nextFloat() * 0.3f
            ))
        }
    }
}
