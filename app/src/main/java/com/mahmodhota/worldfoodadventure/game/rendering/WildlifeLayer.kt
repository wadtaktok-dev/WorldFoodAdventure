package com.mahmodhota.worldfoodadventure.game.rendering

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import com.mahmodhota.worldfoodadventure.game.LivingWorldManager

@Composable
fun WildlifeLayer(manager: LivingWorldManager) {
    val animalPaint = remember { Paint().apply { textAlign = Paint.Align.CENTER } }
    Canvas(Modifier.fillMaxSize()) {
        val h = size.height
        try {
            for (i in manager.animals.indices) {
                val animal = manager.animals[i]
                if (!animal.x.isFinite() || !animal.y.isFinite()) continue
                animalPaint.textSize = 90f * animal.scale
                withTransform({ if (animal.dir > 0) scale(-1f, 1f, Offset(animal.x, h * animal.y)) }) {
                    drawContext.canvas.nativeCanvas.drawText(animal.emoji, animal.x, h * animal.y, animalPaint)
                }
            }
            for (i in manager.particles.indices) {
                val p = manager.particles[i]
                if (!p.x.isFinite() || !p.y.isFinite()) continue
                val color = when(p.colorType) { 1 -> Color(0xFFFFC1E3); 2 -> Color(0xFFFFF59D); 3 -> Color.White.copy(0.3f); 4 -> Color(0xFF81C4FA); 5 -> Color(0xFFFFEB3B); 6 -> Color(0xFFA5D6A7); 7 -> Color(0xFFE1F5FE); else -> Color(0xFFFFF9C4) }
                if (p.colorType == 2 || p.colorType == 4 || p.colorType == 7) drawCircle(color.copy(alpha = (p.alpha * 0.4f).coerceIn(0f, 1f)), p.size * 4f, Offset(p.x, p.y))
                drawCircle(color.copy(alpha = p.alpha.coerceIn(0f, 1f)), p.size, Offset(p.x, p.y))
            }
        } catch (e: Exception) { android.util.Log.e("WorldFood", "WildlifeLayer draw failed", e) }
    }
}
