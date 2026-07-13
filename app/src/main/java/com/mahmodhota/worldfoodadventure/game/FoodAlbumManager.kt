package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class FoodAlbumManager(private val saveManager: SaveManager) {
    var discoveredFoods by mutableStateOf(saveManager.getStringSet("discovered_foods_v2", setOf()))
    var discoveryDates by mutableStateOf(loadDiscoveryDates())
    var lastDiscovered by mutableStateOf<FoodInfo?>(null)

    private fun loadDiscoveryDates(): Map<String, Long> {
        val dates = mutableMapOf<String, Long>()
        discoveredFoods.forEach { id ->
            val date = saveManager.getLong("date_$id", 0L)
            if (date != 0L) dates[id] = date
        }
        return dates
    }

    init {
        migrateLegacyData()
    }

    private fun migrateLegacyData() {
        val legacyFoods = saveManager.getStringSet("discovered_foods", setOf())
        if (legacyFoods.isNotEmpty()) {
            val newIds = discoveredFoods.toMutableSet()
            val remainingLegacy = mutableSetOf<String>()
            legacyFoods.forEach { emoji ->
                val candidates = allFoods.filter { it.emoji == emoji }
                if (candidates.size == 1) {
                    newIds.add(candidates[0].id)
                } else if (candidates.size > 1) {
                    // Ambiguous emoji detected. Do not guess. Preserve raw legacy value.
                    remainingLegacy.add(emoji)
                    android.util.Log.w("WorldFood", "Ambiguous food migration for $emoji")
                } else {
                    // Unknown emoji. Keep it just in case.
                    remainingLegacy.add(emoji)
                }
            }
            discoveredFoods = newIds
            saveManager.setStringSet("discovered_foods_v2", discoveredFoods)
            saveManager.setStringSet("discovered_foods", remainingLegacy)
        }
    }

    fun discoverFood(foodId: String): Boolean {
        if (!saveManager.isWriteEnabled) return false
        if (!discoveredFoods.contains(foodId)) {
            val food = allFoods.find { it.id == foodId }
            if (food != null) {
                val now = System.currentTimeMillis()
                discoveredFoods = discoveredFoods + foodId
                discoveryDates = discoveryDates + (foodId to now)
                saveManager.setStringSet("discovered_foods_v2", discoveredFoods)
                saveManager.setLong("date_$foodId", now)
                lastDiscovered = food
                return true
            }
        }
        return false
    }
    
    fun discoverFoodByEmoji(emoji: String, currentCountryId: String): Boolean {
        val cleanEmoji = emoji.trim()
        val food = allFoods.find { it.emoji.trim() == cleanEmoji && it.countryId == currentCountryId }
        return if (food != null) discoverFood(food.id) else false
    }

    fun clearLastDiscovered() {
        lastDiscovered = null
    }

    fun getCompletionPercentage(): Int {
        if (allFoods.isEmpty()) return 0
        return (discoveredFoods.size * 100) / allFoods.size
    }

    fun reset() {
        discoveredFoods.forEach { saveManager.setLong("date_$it", 0L) }
        discoveredFoods = setOf()
        discoveryDates = emptyMap()
        lastDiscovered = null
        saveManager.setStringSet("discovered_foods_v2", setOf())
    }
}
