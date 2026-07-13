package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

class StatisticsManager(private val saveManager: SaveManager) {
    var playTimeSeconds by mutableLongStateOf(saveManager.getLong("stats_time", 0L))
    var foodsCollected by mutableIntStateOf(saveManager.getInt("stats_foods", 0))
    var coinsCollected by mutableIntStateOf(saveManager.getInt("stats_coins", 0))
    var bossesDefeated by mutableIntStateOf(saveManager.getInt("stats_bosses", 0))
    var treasureChestsOpened by mutableIntStateOf(saveManager.getInt("stats_chests", 0))
    var perfectMissions by mutableIntStateOf(saveManager.getInt("stats_perfect", 0))
    var countriesCompleted by mutableIntStateOf(saveManager.getInt("stats_countries", 0))
    var deaths by mutableIntStateOf(saveManager.getInt("stats_deaths", 0))
    var bombHits by mutableIntStateOf(saveManager.getInt("stats_bombs", 0))

    fun addPlayTime(seconds: Long) {
        if (!saveManager.isWriteEnabled) return
        playTimeSeconds += seconds
        saveManager.setLong("stats_time", playTimeSeconds)
    }

    fun trackFood() {
        if (!saveManager.isWriteEnabled) return
        foodsCollected++
        saveManager.setInt("stats_foods", foodsCollected)
    }

    fun trackCoin() {
        if (!saveManager.isWriteEnabled) return
        coinsCollected++
        saveManager.setInt("stats_coins", coinsCollected)
    }

    fun trackBoss() {
        if (!saveManager.isWriteEnabled) return
        bossesDefeated++
        saveManager.setInt("stats_bosses", bossesDefeated)
    }

    fun trackChest() {
        if (!saveManager.isWriteEnabled) return
        treasureChestsOpened++
        saveManager.setInt("stats_chests", treasureChestsOpened)
    }

    fun trackPerfect() {
        if (!saveManager.isWriteEnabled) return
        perfectMissions++
        saveManager.setInt("stats_perfect", perfectMissions)
    }

    fun trackCountry() {
        if (!saveManager.isWriteEnabled) return
        countriesCompleted++
        saveManager.setInt("stats_countries", countriesCompleted)
    }

    fun trackDeath() {
        if (!saveManager.isWriteEnabled) return
        deaths++
        saveManager.setInt("stats_deaths", deaths)
    }

    fun trackBomb() {
        if (!saveManager.isWriteEnabled) return
        bombHits++
        saveManager.setInt("stats_bombs", bombHits)
    }

    fun reset() {
        playTimeSeconds = 0
        foodsCollected = 0
        coinsCollected = 0
        bossesDefeated = 0
        treasureChestsOpened = 0
        perfectMissions = 0
        countriesCompleted = 0
        deaths = 0
        bombHits = 0
    }
}
