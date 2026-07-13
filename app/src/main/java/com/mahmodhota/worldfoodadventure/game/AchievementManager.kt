package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AchievementManager(private val saveManager: SaveManager, private val soundManager: SoundManager) {
    val unlockedAchMap: SnapshotStateMap<String, String?> = mutableStateMapOf<String, String?>().apply {
        allAchievements.forEach { ach ->
            this[ach.id] = saveManager.getString("ach_${ach.id}", null)
        }
    }

    fun unlockAch(id: String, sOn: Boolean) {
        if (!saveManager.isWriteEnabled) return
        if (unlockedAchMap[id] == null) {
            val d = SimpleDateFormat("dd MMM", Locale.getDefault()).format(Date())
            unlockedAchMap[id] = d
            saveManager.setString("ach_$id", d)
            soundManager.play(soundManager.win, sOn)
        }
    }

    fun checkProgress(engine: GameEngine, rewardManager: RewardManager, foodAlbumManager: FoodAlbumManager, progress: WorldProgressManager, sOn: Boolean) {
        if (rewardManager.coins >= 100) unlockAch("coin_collector", sOn)
        if (rewardManager.maxLives >= 5) unlockAch("healthy_player", sOn)
        if (engine.highScore >= 1000) unlockAch("score_master", sOn)
        if (foodAlbumManager.discoveredFoods.size >= allFoods.size && allFoods.isNotEmpty()) unlockAch("master_chef", sOn)
        
        val allCompleted = CountryRepository.getPlayableCountries().all { progress.isCompleted(it.id) }
        if (allCompleted) unlockAch("world_traveler", sOn)
    }

    fun reset() {
        unlockedAchMap.clear()
        allAchievements.forEach { 
            saveManager.setString("ach_${it.id}", "") // Logic for clear
        }
    }
}
