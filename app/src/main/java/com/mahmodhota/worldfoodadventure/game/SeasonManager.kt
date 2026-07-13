package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.*

class SeasonManager {
    var currentSeason by mutableStateOf(calculateSeason())

    private fun calculateSeason(): Season {
        val cal = Calendar.getInstance()
        val month = cal.get(Calendar.MONTH) // 0-indexed
        val day = cal.get(Calendar.DAY_OF_MONTH)

        // Special Events
        if (month == Calendar.OCTOBER && day == 31) return Season.HALLOWEEN
        if (month == Calendar.OCTOBER && day == 16) return Season.WORLD_FOOD_DAY
        if (month == Calendar.DECEMBER && (day >= 20)) return Season.CHRISTMAS

        // Regular Seasons
        return when (month) {
            Calendar.MARCH, Calendar.APRIL, Calendar.MAY -> Season.SPRING
            Calendar.JUNE, Calendar.JULY, Calendar.AUGUST -> Season.SUMMER
            Calendar.SEPTEMBER, Calendar.OCTOBER, Calendar.NOVEMBER -> Season.AUTUMN
            else -> Season.WINTER
        }
    }
}
