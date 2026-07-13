package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.*

class EventManager {
    var activeEventCountryId by mutableStateOf(calculateEventCountryId())

    private fun calculateEventCountryId(): String {
        val cal = Calendar.getInstance()
        val month = cal.get(Calendar.MONTH)
        val playable = CountryRepository.getPlayableCountries()
        if (playable.isEmpty()) return "germany"
        return playable[month % playable.size].id
    }

    fun isCountryEventActive(countryId: String): Boolean {
        return countryId == activeEventCountryId
    }
}
