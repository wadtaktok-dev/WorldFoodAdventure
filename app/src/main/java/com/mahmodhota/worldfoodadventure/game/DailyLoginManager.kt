package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.text.SimpleDateFormat
import java.util.*

class DailyLoginManager(private val saveManager: SaveManager) {
    var streak by mutableIntStateOf(saveManager.getInt("login_streak", 0))
    var lastLoginDate by mutableStateOf(saveManager.getString("last_login_date", ""))
    var currentDayIndex by mutableIntStateOf(saveManager.getInt("current_reward_day", 0))
    var rewardClaimedToday by mutableStateOf(saveManager.getBoolean("reward_claimed_today", false))

    val rewards = listOf(
        Reward.Coins(50),
        Reward.Score(200), 
        Reward.Coins(100),
        Reward.Heart,
        Reward.Coins(250),
        Reward.Score(500),
        Reward.Coins(1000)
    )

    fun checkLogin() {
        if (!saveManager.isWriteEnabled) return
        val sdf = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val today = sdf.format(Date())
        val lastDateStr = lastLoginDate ?: ""

        if (lastDateStr != today) {
            // New day
            if (isYesterday(lastDateStr)) {
                streak++
            } else if (lastDateStr.isNotEmpty()) {
                streak = 1
            } else {
                streak = 1
            }

            rewardClaimedToday = false
            currentDayIndex %= 7
            
            lastLoginDate = today
            saveManager.setString("last_login_date", today)
            saveManager.setInt("login_streak", streak)
            saveManager.setBoolean("reward_claimed_today", false)
            saveManager.setInt("current_reward_day", currentDayIndex)
        }
    }

    private fun isYesterday(lastDate: String): Boolean {
        if (lastDate.isEmpty()) return false
        val sdf = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        return try {
            val cal = Calendar.getInstance()
            cal.add(Calendar.DATE, -1)
            sdf.format(cal.time) == lastDate
        } catch (e: Exception) {
            false
        }
    }

    fun claimReward(onClaim: (Reward) -> Unit) {
        if (!saveManager.isWriteEnabled) return
        if (!rewardClaimedToday) {
            val reward = rewards[currentDayIndex]
            onClaim(reward)
            rewardClaimedToday = true
            currentDayIndex = (currentDayIndex + 1) % 7
            
            saveManager.setBoolean("reward_claimed_today", true)
            saveManager.setInt("current_reward_day", currentDayIndex)
        }
    }

    fun reset() {
        streak = 0
        lastLoginDate = ""
        currentDayIndex = 0
        rewardClaimedToday = false
        saveManager.setInt("login_streak", 0)
        saveManager.setString("last_login_date", "")
        saveManager.setInt("current_reward_day", 0)
        saveManager.setBoolean("reward_claimed_today", false)
    }
}
