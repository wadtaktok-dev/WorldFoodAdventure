package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class PlayerHomelandManager(private val saveManager: SaveManager) {
    var homeCountryId by mutableStateOf(saveManager.getString("home_country_id", null))

    fun setHomeCountry(id: String?) {
        if (!saveManager.isWriteEnabled) return
        homeCountryId = id
        if (id != null) {
            saveManager.setString("home_country_id", id)
        } else {
            // No direct clear for a single key if not needed, but setString handles it
            saveManager.setString("home_country_id", "")
        }
    }
}
