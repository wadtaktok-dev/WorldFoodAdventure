package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.mutableStateMapOf
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MuseumManager(private val saveManager: SaveManager) {
    val bossDefeatCounts = mutableStateMapOf<String, Int>().apply {
        CountryRepository.allCountries.forEach { c ->
            this[c.id] = saveManager.getInt("boss_def_${c.id}", 0)
        }
    }
    
    val bossBestTimes = mutableStateMapOf<String, Float>().apply {
        CountryRepository.allCountries.forEach { c ->
            this[c.id] = saveManager.getInt("boss_time_${c.id}", 0).toFloat() / 10f
        }
    }

    val foodDiscoveryDates = mutableStateMapOf<String, String>().apply {
        allFoods.forEach { f ->
            // Try new ID-based key first
            saveManager.getString("food_date_v2_${f.id}", null)?.let { 
                this[f.id] = it 
            } ?: run {
                // Fallback to legacy emoji key and migrate
                saveManager.getString("food_date_${f.emoji}", null)?.let { legacyDate ->
                    this[f.id] = legacyDate
                    saveManager.setString("food_date_v2_${f.id}", legacyDate)
                    // saveManager.setString("food_date_${f.emoji}", "") // Optional: clear legacy
                }
            }
        }
    }

    fun trackBossDefeat(countryId: String, time: Float) {
        if (!saveManager.isWriteEnabled) return
        val currentCount = bossDefeatCounts[countryId] ?: 0
        bossDefeatCounts[countryId] = currentCount + 1
        saveManager.setInt("boss_def_$countryId", currentCount + 1)

        val bestTime = bossBestTimes[countryId] ?: 0f
        if (bestTime == 0f || time < bestTime) {
            bossBestTimes[countryId] = time
            saveManager.setInt("boss_time_$countryId", (time * 10).toInt())
        }
    }

    fun trackFoodDiscovery(foodId: String) {
        if (!saveManager.isWriteEnabled) return
        if (foodDiscoveryDates[foodId] == null) {
            val date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date())
            foodDiscoveryDates[foodId] = date
            saveManager.setString("food_date_v2_$foodId", date)
        }
    }

    fun reset() {
        bossDefeatCounts.clear()
        bossBestTimes.clear()
        foodDiscoveryDates.clear()
    }
}
