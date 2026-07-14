package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.mutableStateListOf
import kotlin.random.Random

data class ActiveNpc(
    val id: String, 
    var x: Float, 
    var y: Float, 
    val speed: Float, 
    val emoji: String, 
    val dir: Int,
    var state: String = "WALK", // WALK, STOP, ACTION
    var stateTimer: Float = 0f
)

class NpcManager {
    val activeNpcs = mutableStateListOf<ActiveNpc>()
    private var npcSpawnTimer = 0f
    private var nextSpawnDelay = 15f
    
    private val globalNpcs = listOf(
        NpcInfo("chef", "👨‍🍳", 75f, 0.86f),
        NpcInfo("dog", "🐕", 95f, 0.89f),
        NpcInfo("bicycle", "🚲", 140f, 0.83f),
        NpcInfo("tourist", "📸", 55f, 0.87f),
        NpcInfo("bird_ground", "🐦", 45f, 0.93f),
        NpcInfo("child", "🧒", 75f, 0.88f),
        NpcInfo("photographer", "📷", 40f, 0.87f),
        NpcInfo("cyclist", "🚴", 160f, 0.84f),
        NpcInfo("musician", "🎸", 50f, 0.87f),
        NpcInfo("painter", "🎨", 45f, 0.88f),
        NpcInfo("student", "🎒", 75f, 0.87f)
    )

    private val countrySpecificNpcs = mapOf(
        "italy" to listOf(
            NpcInfo("cat", "🐱", 60f, 0.90f), 
            NpcInfo("tourist_italy", "🤳", 55f, 0.87f),
            NpcInfo("gondolier", "🛶", 45f, 0.85f),
            NpcInfo("elder_italy", "👵", 30f, 0.88f)
        ),
        "germany" to listOf(
            NpcInfo("squirrel", "🐿️", 85f, 0.91f),
            NpcInfo("deer", "🦌", 105f, 0.87f),
            NpcInfo("hiker", "🥾", 65f, 0.86f),
            NpcInfo("baker", "🥖", 55f, 0.87f)
        ),
        "japan" to listOf(
            NpcInfo("cat_japan", "🐱", 60f, 0.90f), 
            NpcInfo("kimono", "🎎", 50f, 0.87f),
            NpcInfo("monk", "🧘", 35f, 0.88f),
            NpcInfo("student_japan", "🏫", 75f, 0.87f)
        ),
        "brazil" to listOf(
            NpcInfo("parrot", "🦜", 160f, 0.22f), 
            NpcInfo("dancer", "💃", 80f, 0.86f),
            NpcInfo("surfer", "🏄", 100f, 0.84f)
        ),
        "sudan" to listOf(
            NpcInfo("chef_sudan", "👨🏿‍🍳", 65f, 0.85f), 
            NpcInfo("camel", "🐫", 55f, 0.88f),
            NpcInfo("elder_sudan", "👴🏿", 30f, 0.88f)
        )
    )

    fun update(dt: Float, screenWidth: Float, screenHeight: Float, currentCountryId: String?, bottomSafeZonePx: Float) {
        if (screenWidth <= 0 || screenHeight <= 0) return
        val clampedDt = dt.coerceAtMost(0.05f)

        // Update movement
        try {
            for (i in activeNpcs.indices.reversed()) {
                val npc = activeNpcs[i]
                
                if (!npc.x.isFinite() || !npc.y.isFinite()) {
                    activeNpcs.removeAt(i)
                    continue
                }

                when(npc.state) {
                    "WALK" -> {
                        npc.x += npc.speed * clampedDt * npc.dir
                        
                        // Chance to stop
                        val stopChance = when(npc.id) {
                            "tourist", "photographer", "tourist_italy" -> 0.008f
                            "chef", "chef_sudan", "baker" -> 0.004f
                            "musician", "painter" -> 0.002f
                            else -> 0.003f
                        }
                        
                        if (Random.nextFloat() < stopChance) {
                            npc.state = "STOP"
                            npc.stateTimer = 2f + Random.nextFloat() * 4f
                        }
                    }
                    "STOP" -> {
                        npc.stateTimer -= clampedDt
                        if (npc.stateTimer <= 0) {
                            npc.state = "WALK"
                        }
                    }
                }
                
                // Bounds removal
                if (npc.dir > 0 && npc.x > screenWidth + 450) activeNpcs.removeAt(i)
                else if (npc.dir < 0 && npc.x < -450) activeNpcs.removeAt(i)
            }
        } catch (e: Exception) {
            android.util.Log.e("NpcManager", "Error updating NPCs", e)
        }

        // Spawning logic
        npcSpawnTimer += clampedDt
        if (npcSpawnTimer >= nextSpawnDelay) {
            if (activeNpcs.size < 2) {
                spawnNpc(screenWidth, screenHeight, currentCountryId, bottomSafeZonePx)
                npcSpawnTimer = 0f
                nextSpawnDelay = 15f + Random.nextFloat() * 20f
            }
        }
    }

    private fun spawnNpc(screenWidth: Float, screenHeight: Float, countryId: String?, bottomSafeZonePx: Float) {
        val possible = globalNpcs.toMutableList()
        countryId?.let { countrySpecificNpcs[it]?.let { specific -> possible.addAll(specific) } }
        
        val type = possible.random()
        // Special case for deer (it should be rare as an NPC if it's already in animals, 
        // but here it's listed in germanySpecificNpcs. Let's keep it but slightly rare.)
        if (type.id == "deer" && Random.nextFloat() > 0.2f) return

        val dir = if (Random.nextBoolean()) 1 else -1
        val startX = if (dir > 0) -400f else screenWidth + 400f
        
        // Calculate Y based on safe zone
        val yBase = screenHeight - bottomSafeZonePx
        val yPos = yBase - (1.0f - type.y) * yBase - 100f

        activeNpcs.add(ActiveNpc(type.id, startX, yPos, type.speed, type.emoji, dir))
    }

    fun reset() {
        activeNpcs.clear()
        npcSpawnTimer = 0f
    }
}
