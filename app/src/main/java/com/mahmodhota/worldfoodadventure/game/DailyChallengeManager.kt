package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DailyChallengeManager(private val saveManager: SaveManager) {
    val dayKey = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date())
    
    var dailyProg by mutableIntStateOf(saveManager.getInt("d_pr_$dayKey", 0))
    var dailyClaimed by mutableStateOf(saveManager.getBoolean("d_cl_$dayKey", false))
    
    val currentDaily = run {
        val list = listOf(
            DailyChallenge("f", "Collect 10 foods", 10),
            DailyChallenge("c", "Collect 20 coins", 20)
        )
        list[Calendar.getInstance().get(Calendar.DAY_OF_YEAR) % list.size]
    }

    fun incrementProgress() {
        if (!saveManager.isWriteEnabled) return
        if (dailyProg < currentDaily.target) {
            dailyProg++
            saveManager.setInt("d_pr_$dayKey", dailyProg)
        }
    }

    fun claimReward(onClaim: (Int) -> Unit) {
        if (!saveManager.isWriteEnabled) return
        if (!dailyClaimed && dailyProg >= currentDaily.target) {
            dailyClaimed = true
            saveManager.setBoolean("d_cl_$dayKey", true)
            onClaim(25)
        }
    }

    fun reset() {
        dailyProg = 0
        dailyClaimed = false
    }
}
