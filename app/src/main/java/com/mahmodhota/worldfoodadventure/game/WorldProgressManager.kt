package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.mutableStateMapOf

class WorldProgressManager(private val saveManager: SaveManager) {
    
    // In-memory cache of completion status
    val completionStatus = mutableStateMapOf<String, Boolean>()
    val visitedStatus = mutableStateMapOf<String, Boolean>()

    init {
        migrateLegacyData()
        loadProgress()
    }

    private fun migrateLegacyData() {
        // Map old UPPERCASE enum keys to new lowercase IDs
        val legacyKeys = listOf("GERMANY", "ITALY", "JAPAN", "FRANCE", "SPAIN", "GREECE", "MEXICO", "INDIA", "CHINA", "THAILAND", "TURKEY", "BRAZIL", "USA", "SUDAN")
        legacyKeys.forEach { legacy ->
            val newId = legacy.lowercase()
            
            // Migrate completion
            if (saveManager.getBoolean("unl_$legacy", false)) {
                saveManager.setBoolean("comp_$newId", true)
                saveManager.setBoolean("unl_$legacy", false) // Clear legacy once migrated
            }
            
            // Migrate visited
            if (saveManager.getBoolean("visited_$legacy", false)) {
                saveManager.setBoolean("vis_$newId", true)
                saveManager.setBoolean("visited_$legacy", false) // Clear legacy
            }
        }
        
        // Migrate lowercase legacy from WorldMapManager
        CountryRepository.allCountries.forEach { country ->
            val id = country.id
            if (saveManager.getBoolean("unl_$id", false)) {
                saveManager.setBoolean("comp_$id", true)
                saveManager.setBoolean("unl_$id", false)
            }
            if (saveManager.getBoolean("visited_$id", false)) {
                saveManager.setBoolean("vis_$id", true)
                saveManager.setBoolean("visited_$id", false)
            }
        }
    }

    private fun loadProgress() {
        CountryRepository.allCountries.forEach { country ->
            completionStatus[country.id] = saveManager.getBoolean("comp_${country.id}", false)
            visitedStatus[country.id] = saveManager.getBoolean("vis_${country.id}", false)
        }
    }

    fun isUnlocked(country: CountryData): Boolean {
        if (country.id == "germany") return true
        val playable = CountryRepository.getPlayableCountries()
        val index = playable.indexOf(country)
        if (index <= 0) return false
        val prev = playable[index - 1]
        return isCompleted(prev.id)
    }

    fun isCompleted(id: String): Boolean = completionStatus[id] ?: false

    fun completeMission(id: String) {
        if (!saveManager.isWriteEnabled) return
        completionStatus[id] = true
        saveManager.setBoolean("comp_$id", true)
    }

    fun markVisited(id: String) {
        if (!saveManager.isWriteEnabled) return
        visitedStatus[id] = true
        saveManager.setBoolean("vis_$id", true)
    }
    
    fun getCompletionPercentage(): Int {
        val total = CountryRepository.getPlayableCountries().size
        if (total == 0) return 0
        val completed = completionStatus.values.count { it }
        return (completed * 100) / total
    }

    fun getCountryCompletionStats(country: CountryData, foodAlbum: FoodAlbumManager, stats: StatisticsManager): CountryStats {
        val isComp = isCompleted(country.id)
        val foodsDiscovered = country.foods.count { foodAlbum.discoveredFoods.contains(it.id) }
        val foodPercent = if (country.foods.isEmpty()) 100 else (foodsDiscovered * 100) / country.foods.size
        
        // Stars calculation (simplistic logic for now)
        var stars = 0
        if (isComp) stars++
        if (foodPercent >= 100) stars++
        if (stats.coinsCollected > 500) stars++ // Example global coin check for star

        return CountryStats(
            missionPercent = if (isComp) 100 else 0,
            foodPercent = foodPercent,
            museumPercent = foodPercent, // Simplification: museum is based on food discovery
            coinsPercent = (stats.coinsCollected.coerceAtMost(1000) * 100) / 1000,
            stars = stars
        )
    }

    fun getExplorerTitle(): String {
        val completed = completionStatus.values.count { it }
        return when {
            completed >= CountryRepository.getPlayableCountries().size -> "World Explorer"
            completed >= 10 -> "Global Nomad"
            completed >= 5 -> "Country Hopper"
            else -> "Local Tourist"
        }
    }
}

data class CountryStats(
    val missionPercent: Int,
    val foodPercent: Int,
    val museumPercent: Int,
    val coinsPercent: Int,
    val stars: Int
)
