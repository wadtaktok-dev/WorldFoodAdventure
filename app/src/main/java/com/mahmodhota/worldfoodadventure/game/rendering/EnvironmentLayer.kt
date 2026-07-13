package com.mahmodhota.worldfoodadventure.game.rendering

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.nativeCanvas
import com.mahmodhota.worldfoodadventure.game.EnvironmentManager

@Composable
fun EnvironmentLayer(manager: EnvironmentManager) {
    val paint = remember { Paint().apply { textAlign = Paint.Align.CENTER } }
    Canvas(Modifier.fillMaxSize()) {
        try {
            for (i in manager.skyObjects.indices) {
                val obj = manager.skyObjects[i]
                if (!obj.x.isFinite() || !obj.y.isFinite()) continue
                paint.textSize = 80f * obj.scale
                paint.alpha = (obj.alpha * 255).toInt().coerceIn(0, 255)
                drawContext.canvas.nativeCanvas.drawText(obj.emoji, obj.x, obj.y, paint)
            }
        } catch (e: Exception) { android.util.Log.e("WorldFood", "EnvironmentLayer draw failed", e) }
    }
}
