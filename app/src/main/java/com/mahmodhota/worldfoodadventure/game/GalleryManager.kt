package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.mutableStateMapOf
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class MemoryMilestone(val id: String, val title: String, val emoji: String)

class GalleryManager(private val saveManager: SaveManager) {
    val unlockedMemories = mutableStateMapOf<String, String>().apply {
        milestones.forEach { m ->
            saveManager.getString("mem_${m.id}", null)?.let { this[m.id] = it }
        }
    }

    fun unlockMemory(id: String) {
        if (unlockedMemories[id] == null) {
            val date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date())
            unlockedMemories[id] = date
            saveManager.setString("mem_$id", date)
        }
    }

    fun reset() {
        unlockedMemories.clear()
    }

    companion object {
        val milestones = listOf(
            MemoryMilestone("first_boss", "First Boss Defeated", "🏆"),
            MemoryMilestone("first_country", "First Country Mastered", "🌍"),
            MemoryMilestone("first_treasure", "First Treasure Opened", "📦"),
            MemoryMilestone("level_10", "Level 10 Reached", "⭐"),
            MemoryMilestone("all_foods", "All Foods in a Country", "🍽️"),
        )
    }
}
