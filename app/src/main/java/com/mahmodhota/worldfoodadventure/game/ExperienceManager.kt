package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ExperienceManager(private val saveManager: SaveManager) {
    var totalXp by mutableIntStateOf(saveManager.getInt("player_xp", 0))
    var currentLevel by mutableIntStateOf(calculateLevel(totalXp))
    var lastUnlockedTitle by mutableStateOf(allRanks.find { it.level == currentLevel }?.title ?: "Home Cook")

    private fun calculateLevel(xp: Int): Int {
        var level = 1
        allRanks.forEach { rank ->
            if (xp >= rank.xpRequired) {
                level = rank.level
            }
        }
        return level
    }

    fun addXp(amount: Int): Boolean {
        if (!saveManager.isWriteEnabled) return false
        totalXp += amount
        saveManager.setInt("player_xp", totalXp)
        
        val newLevel = calculateLevel(totalXp)
        if (newLevel > currentLevel) {
            currentLevel = newLevel
            lastUnlockedTitle = allRanks.find { it.level == currentLevel }?.title ?: "Home Cook"
            return true // Level Up!
        }
        return false
    }

    fun getXpProgress(): Float {
        val currentRank = allRanks.find { it.level == currentLevel } ?: return 0f
        val nextRank = allRanks.find { it.level == (currentLevel + 1) } ?: return 1f
        
        val range = nextRank.xpRequired - currentRank.xpRequired
        val progress = totalXp - currentRank.xpRequired
        return (progress.toFloat() / range.toFloat()).coerceIn(0f, 1f)
    }

    fun reset() {
        totalXp = 0
        currentLevel = 1
        lastUnlockedTitle = "Home Cook"
    }
}
