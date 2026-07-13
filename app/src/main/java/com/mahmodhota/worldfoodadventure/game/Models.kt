package com.mahmodhota.worldfoodadventure.game

data class FeedbackEntry(
    val id: String,
    val rating: Int,
    val category: String,
    val comment: String,
    val timestamp: Long,
    val screenshotPath: String? = null
)

data class CartSkin(val id: String, val name: String, val price: Int, val drawable: String)

data class Achievement(val id: String, val title: String, val desc: String, val icon: String, val tier: String)

data class DailyChallenge(val id: String, val desc: String, val target: Int)

sealed class Reward(val title: String, val icon: String) {
    class Coins(val amount: Int) : Reward("+$amount Coins", "🪙")
    object Heart : Reward("+1 Max Heart", "❤️")
    class Score(val pts: Int) : Reward("+$pts Bonus Score", "⭐")
    class Food(val foodId: String, val iconEmoji: String) : Reward("Rare Food Found!", iconEmoji)
    class Skin(val skin: CartSkin) : Reward("New Skin Unlocked!", "✨")
    class Music(val music: MusicStyle) : Reward("New Track Unlocked!", "🎶")
}

data class FoodInfo(val id: String, val emoji: String, val name: String, val countryId: String, val desc: String, val ingredients: List<String>, val nutrition: String)

data class Boss(val name: String, val emoji: String, val health: Int, val countryId: String, val attacks: List<String>)

data class BossAttack(var x: Float, var y: Float, val emoji: String)

data class PlayerRank(val level: Int, val title: String, val xpRequired: Int)

data class NpcInfo(val id: String, val emoji: String, val speed: Float, val y: Float)

data class GameItem(val x: Float, val y: Float, val emoji: String, val isObstacle: Boolean, val size: Float = 100f, val foodId: String? = null)
