package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.math.abs

class LiveShowcaseManager {
    var isRunning by mutableStateOf(false)
    var isPaused by mutableStateOf(false)
    var manualOverrideTimer by mutableFloatStateOf(0f)
    var aiTargetX by mutableFloatStateOf(0.5f)
    
    private var lastCalculationTime = 0L

    fun start() {
        isRunning = true
        isPaused = false
        manualOverrideTimer = 0f
    }

    fun stop() {
        isRunning = false
        isPaused = false
    }

    fun pause() {
        if (isRunning) isPaused = true
    }

    fun resume() {
        if (isRunning) isPaused = false
    }

    fun update(dt: Float, pX: Float, sW: Float, sH: Float, items: List<GameItem>) {
        if (!isRunning || isPaused) return

        if (manualOverrideTimer > 0) {
            manualOverrideTimer -= dt
            return
        }

        val now = System.currentTimeMillis()
        if (now - lastCalculationTime < 150) return
        lastCalculationTime = now

        if (items.isEmpty()) {
            aiTargetX = 0.5f
            return
        }

        // Logic: Dodge bombs, pick collectibles
        val dangerZoneY = sH - 550f
        val dangerousBomb = items.filter { it.isObstacle && it.y > dangerZoneY }
            .minByOrNull { abs(it.x - (pX * sW)) }

        if (dangerousBomb != null && abs(dangerousBomb.x - (pX * sW)) < 180f) {
            aiTargetX = if (dangerousBomb.x > pX * sW) {
                (pX - 0.35f).coerceIn(0.1f, 0.9f)
            } else {
                (pX + 0.35f).coerceIn(0.1f, 0.9f)
            }
        } else {
            val collectible = items.filter { !it.isObstacle && it.y > 0 }
                .minByOrNull { 
                    val dx = it.x - (pX * sW)
                    val dy = it.y - (sH - 200f)
                    dx * dx + dy * dy 
                }
            
            if (collectible != null) {
                aiTargetX = (collectible.x / sW).coerceIn(0.1f, 0.9f)
            }
        }
    }

    fun onManualInput() {
        if (isRunning) {
            manualOverrideTimer = 5f // Manual control takes over for 5s
        }
    }
}
