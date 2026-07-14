package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import kotlin.math.sin
import kotlin.random.Random

data class DeluxeAnimal(
    val id: String, 
    var x: Float, 
    var y: Float, 
    val speed: Float, 
    val emoji: String, 
    val dir: Int, 
    val type: String, 
    val scale: Float = 1f,
    var state: String = "MOVE", // MOVE, PAUSE, IDLE
    var pauseTimer: Float = 0f,
    var behaviorTimer: Float = 0f,
    var baseSpeed: Float = 0f
)

data class Particle(val id: String, var x: Float, var y: Float, val vx: Float, val vy: Float, val size: Float, var alpha: Float, val colorType: Int)

class LivingWorldManager {
    val animals = mutableStateListOf<DeluxeAnimal>()
    val particles = mutableStateListOf<Particle>()
    var vegetationSway by mutableFloatStateOf(0f)
    var waterWave by mutableFloatStateOf(0f)
    var lightPulse by mutableFloatStateOf(0f)
    private var time = 0f
    private var animalSpawnTimer = 0f
    private var nextSpawnDelay = 10f

    fun update(dt: Float, screenWidth: Float, screenHeight: Float, countryId: String?, bottomSafeZonePx: Float) {
        if (screenWidth <= 0 || screenHeight <= 0) return
        val clampedDt = dt.coerceAtMost(0.05f)
        time += clampedDt
        vegetationSway = sin(time * 0.6f) * 8f 
        waterWave = sin(time * 0.4f) * 12f
        lightPulse = 0.85f + sin(time * 0.3f) * 0.15f

        // Animals update
        try {
            for (i in animals.indices.reversed()) {
                val a = animals[i]
                
                if (!a.x.isFinite() || !a.y.isFinite()) {
                    animals.removeAt(i)
                    continue
                }

                when(a.state) {
                    "MOVE" -> {
                        a.x += a.speed * clampedDt * a.dir
                        
                        // Behavior variations based on type
                        when(a.type) {
                            "bird" -> {
                                // fly in gentle waves
                                a.y += sin(time * 2.5f + a.id.hashCode()) * 45f * clampedDt
                                // occasionally glide (reduce wave amplitude)
                                if (sin(time * 0.5f + a.id.hashCode()) > 0.7f) {
                                    a.y -= sin(time * 2.5f + a.id.hashCode()) * 20f * clampedDt
                                }
                            }
                            "insect" -> {
                                a.y += sin(time * 5f + a.id.hashCode()) * 60f * clampedDt
                                a.x += sin(time * 3f + a.id.hashCode()) * 30f * clampedDt
                                
                                // Pause on "flower" (random spot)
                                if (Random.nextFloat() < 0.005f) {
                                    a.state = "PAUSE"
                                    a.pauseTimer = 1f + Random.nextFloat() * 2f
                                }
                            }
                            "ground" -> {
                                if (Random.nextFloat() < 0.003f) {
                                    a.state = "PAUSE"
                                    a.pauseTimer = 2f + Random.nextFloat() * 3f
                                }
                            }
                        }
                    }
                    "PAUSE" -> {
                        a.pauseTimer -= clampedDt
                        if (a.pauseTimer <= 0) {
                            a.state = "MOVE"
                        }
                    }
                }
                
                // Bounds removal
                if (a.dir > 0 && a.x > screenWidth + 350) animals.removeAt(i)
                else if (a.dir < 0 && a.x < -350) animals.removeAt(i)
            }
        } catch (e: Exception) {
            android.util.Log.e("LivingWorld", "Error updating animals", e)
        }

        // Animal spawning logic
        animalSpawnTimer += clampedDt
        if (animalSpawnTimer >= nextSpawnDelay && countryId != null) {
            if (animals.size < 3) {
                spawnAnimal(screenWidth, screenHeight, countryId, bottomSafeZonePx)
                animalSpawnTimer = 0f
                nextSpawnDelay = 12f + Random.nextFloat() * 15f // Gaps between spawns
            }
        }

        // Particles spawning
        if (particles.size < 15 && Random.nextFloat() < 0.05f) {
            val type = Random.nextInt(8)
            particles.add(Particle(
                id = Random.nextInt().toString(),
                x = Random.nextFloat() * screenWidth,
                y = Random.nextFloat() * (screenHeight - bottomSafeZonePx - 250f),
                vx = (Random.nextFloat() - 0.5f) * 40f,
                vy = 20f + Random.nextFloat() * 60f,
                size = 1.5f + Random.nextFloat() * 6f,
                alpha = 0.1f + Random.nextFloat() * 0.7f,
                colorType = type
            ))
        }
        
        // Particles update
        try {
            for (i in particles.indices.reversed()) {
                val p = particles[i]
                if (!p.x.isFinite() || !p.y.isFinite()) {
                    particles.removeAt(i)
                    continue
                }

                p.x += p.vx * clampedDt + sin(time * 0.8f + p.y * 0.01f) * 12f * clampedDt
                p.y += p.vy * clampedDt
                
                if (p.colorType == 2 || p.colorType == 5 || p.colorType == 7) {
                    p.alpha = 0.2f + sin(time * 5f + p.x * 0.05f) * 0.25f
                }
                
                if (p.y > screenHeight - bottomSafeZonePx || p.x < -200f || p.x > screenWidth + 200f) {
                    particles.removeAt(i)
                }
            }
        } catch (e: Exception) {
            android.util.Log.e("LivingWorld", "Error updating particles", e)
        }
    }

    private fun spawnAnimal(screenWidth: Float, screenHeight: Float, countryId: String, bottomSafeZonePx: Float) {
        val birdCount = animals.count { it.type == "bird" }
        val insectCount = animals.count { it.type == "insect" }
        val groundCount = animals.count { it.type == "ground" }

        val possibleTypes = mutableListOf<String>()
        if (birdCount < 2) possibleTypes.add("bird")
        if (insectCount < 2) possibleTypes.add("insect")
        if (groundCount < 2) possibleTypes.add("ground")

        if (possibleTypes.isEmpty()) return
        val chosenType = possibleTypes.random()

        val emoji = when(chosenType) {
            "bird" -> when(countryId) {
                "sudan" -> listOf("🦅", "🐦").random()
                "germany" -> listOf("🐦", "🦅").random()
                "japan" -> listOf("🦢", "🦆").random()
                else -> listOf("🕊️", "🐦").random()
            }
            "insect" -> listOf("🦋", "🐝").random()
            "ground" -> when(countryId) {
                "italy" -> listOf("🐈", "🐕").random()
                "germany" -> listOf("🐿️", "🦌", "🦊", "🐇").random()
                "japan" -> listOf("🐈", "🦋").random()
                "sudan" -> listOf("🐪", "🐫", "🦎").random()
                "brazil" -> listOf("🐒", "🐆").random()
                "thailand" -> listOf("🐘", "🐒").random()
                else -> listOf("🐿️", "🐇").random()
            }
            else -> "🦋"
        }

        val dir = if (Random.nextBoolean()) 1 else -1
        val startX = if (dir > 0) -300f else screenWidth + 300f
        
        val baseSpeed = when(chosenType) {
            "bird" -> 140f + Random.nextFloat() * 60f
            "insect" -> 50f + Random.nextFloat() * 30f
            "ground" -> if (emoji == "🐪" || emoji == "🐫") 30f + Random.nextFloat() * 20f else 70f + Random.nextFloat() * 40f
            else -> 60f
        }

        val yRange = screenHeight - bottomSafeZonePx
        val startY = when(chosenType) {
            "bird" -> Random.nextFloat() * (yRange * 0.35f) + 60f
            "insect" -> Random.nextFloat() * (yRange * 0.5f) + 120f
            "ground" -> yRange - 160f - Random.nextFloat() * 40f
            else -> yRange * 0.5f
        }

        animals.add(DeluxeAnimal(
            id = Random.nextInt().toString(),
            x = startX,
            y = startY,
            speed = baseSpeed,
            emoji = emoji,
            dir = dir,
            type = chosenType,
            scale = 0.85f + Random.nextFloat() * 0.4f,
            baseSpeed = baseSpeed
        ))
    }
}
