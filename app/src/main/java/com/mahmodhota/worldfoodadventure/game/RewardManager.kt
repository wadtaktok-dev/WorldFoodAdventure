package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.random.Random

class RewardManager(private val saveManager: SaveManager) {
    var coins by mutableIntStateOf(saveManager.getInt("coins", 0))
    var lifetimePoints by mutableIntStateOf(saveManager.getInt("lifetime", 0))
    var maxLives by mutableIntStateOf(saveManager.getInt("max_lives", 3))
    var rareFoods by mutableStateOf(saveManager.getStringSet("rare_foods", setOf()))
    var unlockedMusic by mutableStateOf(saveManager.getStringSet("unl_mus", setOf(MusicStyle.INTERNATIONAL.name)))

    fun generateReward(country: CountryData, purchasedSkins: Set<String>, isChallenge: Boolean = false): Reward {
        val rVal = if (isChallenge) Random.nextInt(40, 100) else Random.nextInt(100)
        return when {
            rVal < 30 -> Reward.Coins(25)
            rVal < 50 -> Reward.Coins(60)
            rVal < 65 -> Reward.Heart
            rVal < 75 -> Reward.Score(150)
            rVal < 88 -> {
                val rareFood = country.foods.find { it.isRare } ?: FoodData("generic_gem", "💎", "Gem", "Precious reward")
                Reward.Food(rareFood.id, rareFood.emoji)
            }
            rVal < 94 -> {
                val u = allSkins.filter { !purchasedSkins.contains(it.id) }
                if (u.isNotEmpty()) Reward.Skin(u.random()) else Reward.Coins(250)
            }
            else -> {
                val l = MusicStyle.entries.filter { !unlockedMusic.contains(it.name) }
                if (l.isNotEmpty()) Reward.Music(l.random()) else Reward.Coins(200)
            }
        }
    }

    fun claimReward(reward: Reward, shopManager: ShopManager) {
        if (!saveManager.isWriteEnabled) return
        when (reward) {
            is Reward.Coins -> addCoins(reward.amount)
            is Reward.Heart -> {
                maxLives = (maxLives + 1).coerceAtMost(5)
                saveManager.setInt("max_lives", maxLives)
            }
            is Reward.Score -> addPoints(reward.pts)
            is Reward.Food -> {
                rareFoods = rareFoods + reward.foodId
                saveManager.setStringSet("rare_foods", rareFoods)
            }
            is Reward.Skin -> {
                shopManager.purchasedSkins = shopManager.purchasedSkins + reward.skin.id
                saveManager.setStringSet("skins", shopManager.purchasedSkins)
            }
            is Reward.Music -> {
                unlockedMusic = unlockedMusic + reward.music.name
                saveManager.setStringSet("unl_mus", unlockedMusic)
            }
        }
    }
    
    fun addCoins(amount: Int) {
        if (!saveManager.isWriteEnabled) return
        coins += amount
        saveManager.setInt("coins", coins)
        if (coins >= 100) {
            // Achievement check can happen here or in engine
        }
    }

    fun addPoints(amount: Int) {
        if (!saveManager.isWriteEnabled) return
        lifetimePoints += amount
        saveManager.setInt("lifetime", lifetimePoints)
    }

    fun reset() {
        coins = 0
        lifetimePoints = 0
        maxLives = 3
        rareFoods = setOf()
        unlockedMusic = setOf(MusicStyle.INTERNATIONAL.name)
    }
}
