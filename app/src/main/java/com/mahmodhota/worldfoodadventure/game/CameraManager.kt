package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue

class CameraManager {
    var offsetX by mutableFloatStateOf(0f)
    var offsetY by mutableFloatStateOf(0f)
    var scale by mutableFloatStateOf(1f)

    fun update(dt: Float) {
        // Camera movement disabled as per refined visual requirements
        offsetX = 0f
        offsetY = 0f
        scale = 1f
    }
}
