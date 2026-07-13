package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import kotlin.math.sin
import kotlin.random.Random

data class DeluxeAnimal(val id: String, var x: Float, val y: Float, val speed: Float, val emoji: String, val dir: Int, val scale: Float = 1f)
data class Particle(val id: String, var x: Float, var y: Float, val vx: Float, val vy: Float, val size: Float, var alpha: Float, val colorType: Int)

class LivingWorldManager {
    val animals = mutableStateListOf<DeluxeAnimal>()
    val particles = mutableStateListOf<Particle>()
    var vegetationSway by mutableFloatStateOf(0f)
    var waterWave by mutableFloatStateOf(0f)
    var lightPulse by mutableFloatStateOf(0f)
    private var time = 0f

    fun update(dt: Float, screenWidth: Float, countryId: String?) {
        if (screenWidth <= 0) return
        time += dt
        vegetationSway = sin(time * 0.6f) * 8f 
        waterWave = sin(time * 0.4f) * 12f
        lightPulse = 0.85f + sin(time * 0.3f) * 0.15f

        // Animals
        try {
            for (i in animals.indices.reversed()) {
                val a = animals[i]
                
                // Validate coordinates
                if (!a.x.isFinite() || !a.y.isFinite()) {
                    animals.removeAt(i)
                    continue
                }

                a.x += a.speed * dt * a.dir
                
                // Premium micro-movements
                if (a.emoji == "🦅" || a.emoji == "🕊️") {
                    a.x += sin(time * 2f) * 50f * dt
                }
                
                if (a.speed > 0) {
                    if (a.dir > 0 && a.x > screenWidth + 300) animals.removeAt(i)
                    else if (a.dir < 0 && a.x < -300) animals.removeAt(i)
                }
            }
        } catch (e: Exception) {
            android.util.Log.e("LivingWorld", "Error updating animals", e)
        }

        if (Random.nextFloat() < 0.015f && animals.count { it.speed > 0 } < 10 && countryId != null) {
            val emoji = when(countryId) {
                "italy" -> listOf("🐈", "🕊️", "🕊️", "🐕", "🐦", "🐦", "🦋", "🐈").random()
                "germany" -> listOf("🐿️", "🦌", "🐿️", "🦊", "🦌", "🐦", "🦅", "🐇").random()
                "japan" -> listOf("🦢", "🦆", "🦆", "🦢", "🐈", "🕊️", "🐦", "🦋").random()
                "sudan" -> listOf("🐪", "🦅", "🦅", "🐕", "🐪", "🦎", "🐫", "🐦").random()
                "brazil" -> listOf("🦜", "🦋", "🦜", "🐒", "🦋", "🐒", "🦜", "🐆").random()
                "thailand" -> listOf("🐘", "🐒", "🐘", "🐕", "🐦", "🦋", "🐒").random()
                else -> listOf("🦋", "🐦", "🐿️", "🕊️", "🐝").random()
            }
            val dir = if (Random.nextBoolean()) 1 else -1
            val startX = if (dir > 0) -250f else screenWidth + 250f
            val speed = 40f + Random.nextFloat() * 45f
            animals.add(DeluxeAnimal(Random.nextInt().toString(), startX, 0.82f + Random.nextFloat() * 0.15f, speed, emoji, dir, 0.8f + Random.nextFloat() * 0.4f))
        }

        // Particles (Leaves, Flowers, Sparkles, etc. - Premium 2.0)
        if (particles.size < 30) {
            val type = Random.nextInt(8)
            particles.add(Particle(
                id = Random.nextInt().toString(),
                x = Random.nextFloat() * screenWidth,
                y = Random.nextFloat() * 1600f - 400f,
                vx = (Random.nextFloat() - 0.5f) * 40f,
                vy = 20f + Random.nextFloat() * 60f,
                size = 1.5f + Random.nextFloat() * 6f,
                alpha = 0.1f + Random.nextFloat() * 0.7f,
                colorType = type
            ))
        }
        
        try {
            for (i in particles.indices.reversed()) {
                val p = particles[i]
                
                // Validate coordinates
                if (!p.x.isFinite() || !p.y.isFinite()) {
                    particles.removeAt(i)
                    continue
                }

                p.x += p.vx * dt + sin(time * 0.8f + p.y * 0.01f) * 12f * dt
                p.y += p.vy * dt
                
                // Firefly / Sparkle / Water logic
                if (p.colorType == 2 || p.colorType == 5 || p.colorType == 7) {
                    p.alpha = 0.2f + sin(time * 5f + p.x * 0.05f) * 0.25f
                }
                
                if (p.y > 2300f || p.x < -200f || p.x > screenWidth + 200f) particles.removeAt(i)
            }
        } catch (e: Exception) {
            android.util.Log.e("LivingWorld", "Error updating particles", e)
        }
    }
}
