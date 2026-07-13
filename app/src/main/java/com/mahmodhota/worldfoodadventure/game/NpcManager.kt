package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.mutableStateListOf
import kotlin.random.Random

data class ActiveNpc(val id: String, var x: Float, val y: Float, val speed: Float, val emoji: String, val dir: Int)

class NpcManager {
    val activeNpcs = mutableStateListOf<ActiveNpc>()
    private val spawnChance = 0.006f // Premium 2.0 increase
    
    private val globalNpcs = listOf(
        NpcInfo("chef", "👨‍🍳", 65f, 0.86f),
        NpcInfo("dog", "🐕", 85f, 0.89f),
        NpcInfo("bicycle", "🚲", 130f, 0.83f),
        NpcInfo("tourist", "📸", 45f, 0.87f),
        NpcInfo("bee", "🐝", 100f, 0.38f),
        NpcInfo("butterfly", "🦋", 60f, 0.48f),
        NpcInfo("bird_ground", "🐦", 35f, 0.93f),
        NpcInfo("child", "🧒", 75f, 0.88f),
        NpcInfo("photographer", "📷", 40f, 0.87f),
        NpcInfo("cyclist", "🚴", 150f, 0.84f),
        NpcInfo("running_child", "🏃", 120f, 0.88f),
        NpcInfo("musician", "🎸", 40f, 0.87f),
        NpcInfo("painter", "🎨", 35f, 0.88f),
        NpcInfo("student", "🎒", 65f, 0.87f)
    )

    private val countrySpecificNpcs = mapOf(
        "italy" to listOf(
            NpcInfo("cat", "🐱", 50f, 0.90f), 
            NpcInfo("tourist_italy", "🤳", 45f, 0.87f),
            NpcInfo("gondolier", "🛶", 35f, 0.85f),
            NpcInfo("elder_italy", "👵", 30f, 0.88f)
        ),
        "germany" to listOf(
            NpcInfo("squirrel", "🐿️", 75f, 0.91f),
            NpcInfo("deer", "🦌", 95f, 0.87f),
            NpcInfo("hiker", "🥾", 55f, 0.86f),
            NpcInfo("baker", "🥖", 45f, 0.87f)
        ),
        "japan" to listOf(
            NpcInfo("cat_japan", "🐱", 50f, 0.90f), 
            NpcInfo("kimono", "🎎", 40f, 0.87f),
            NpcInfo("monk", "🧘", 25f, 0.88f),
            NpcInfo("student_japan", "🏫", 65f, 0.87f)
        ),
        "brazil" to listOf(
            NpcInfo("parrot", "🦜", 150f, 0.22f), 
            NpcInfo("dancer", "💃", 70f, 0.86f),
            NpcInfo("surfer", "🏄", 90f, 0.84f)
        ),
        "sudan" to listOf(
            NpcInfo("chef_sudan", "👨🏿‍🍳", 55f, 0.85f), 
            NpcInfo("camel", "🐫", 45f, 0.88f),
            NpcInfo("elder_sudan", "👴🏿", 30f, 0.88f)
        )
    )

    fun update(dt: Float, screenWidth: Float, currentCountryId: String?) {
        if (screenWidth <= 0) return
        
        try {
            for (i in activeNpcs.indices.reversed()) {
                val npc = activeNpcs[i]
                
                // Validate coordinates
                if (!npc.x.isFinite() || !npc.y.isFinite()) {
                    activeNpcs.removeAt(i)
                    continue
                }

                npc.x += npc.speed * dt * npc.dir
                
                if (npc.dir > 0 && npc.x > screenWidth + 400) activeNpcs.removeAt(i)
                else if (npc.dir < 0 && npc.x < -400) activeNpcs.removeAt(i)
            }
        } catch (e: Exception) {
            android.util.Log.e("NpcManager", "Error updating NPCs", e)
        }

        if (Random.nextFloat() < spawnChance && activeNpcs.size < 6) {
            val possible = globalNpcs.toMutableList()
            currentCountryId?.let { countrySpecificNpcs[it]?.let { specific -> possible.addAll(specific) } }
            
            val type = possible.random()
            if (type.id == "deer" && Random.nextFloat() > 0.1f) return

            val dir = if (Random.nextBoolean()) 1 else -1
            val startX = if (dir > 0) -350f else screenWidth + 350f
            activeNpcs.add(ActiveNpc(type.id, startX, type.y, type.speed, type.emoji, dir))
        }
    }

    fun reset() {
        activeNpcs.clear()
    }
}
