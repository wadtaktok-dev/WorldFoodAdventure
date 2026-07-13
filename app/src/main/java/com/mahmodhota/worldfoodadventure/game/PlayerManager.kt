package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class PlayerManager(private val saveManager: SaveManager) {
    var name by mutableStateOf(saveManager.getString("player_name", "New Chef") ?: "New Chef")
    var avatar by mutableStateOf(saveManager.getString("player_avatar", "👨‍🍳") ?: "👨‍🍳")

    fun updateName(newName: String) {
        name = newName
        saveManager.setString("player_name", newName)
    }

    fun updateAvatar(newAvatar: String) {
        avatar = newAvatar
        saveManager.setString("player_avatar", newAvatar)
    }

    fun reset() {
        name = "New Chef"
        avatar = "👨‍🍳"
    }
}
