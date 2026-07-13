package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class DemoManager(private val saveManager: SaveManager) {
    var isEnabled by mutableStateOf(saveManager.getBoolean("demo_enabled", true))
    private var lastInteractionTime = System.currentTimeMillis()
    private val idleThreshold = 20000L // 20 seconds

    fun setDemoEnabled(enabled: Boolean) {
        isEnabled = enabled
        saveManager.setBoolean("demo_enabled", enabled)
    }

    fun onInteraction() {
        lastInteractionTime = System.currentTimeMillis()
    }

    fun shouldStartDemo(currentState: GameState): Boolean {
        if (!isEnabled || currentState != GameState.MENU) return false
        return (System.currentTimeMillis() - lastInteractionTime) > idleThreshold
    }

    fun resetTimer() {
        lastInteractionTime = System.currentTimeMillis()
    }
}
