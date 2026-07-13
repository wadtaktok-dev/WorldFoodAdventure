package com.mahmodhota.worldfoodadventure.game.rendering

import android.graphics.Paint
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import com.mahmodhota.worldfoodadventure.game.WeatherType
import kotlin.math.sin

@Composable
fun IdleVisuals(weather: WeatherType = WeatherType.SUNNY, countryId: String? = null) {
    val inf = rememberInfiniteTransition(label = "idle")
    val time by inf.animateFloat(0f, 100f, infiniteRepeatable(tween(200000, easing = LinearEasing)), label = "t")
    val paint = remember { Paint().apply { textAlign = Paint.Align.CENTER } }
    val waterBrush = remember { Brush.verticalGradient(listOf(Color(0xFF4FC3F7).copy(0.2f), Color(0xFF01579B).copy(0.4f))) }

    Canvas(Modifier.fillMaxSize()) {
        val w = size.width; val h = size.height
        val waterY = h - 220f
        if (countryId == "italy" || countryId == "japan" || countryId == "sudan" || countryId == "brazil") {
            drawRect(waterBrush, topLeft = Offset(0f, waterY), size = Size(w, 220f))
            for (i in 0..6) { val wy = waterY + i * 35f; val off = sin(time * 3.5f + i) * 12f; drawLine(Color.White.copy(0.18f), Offset(0f, wy + off), Offset(w, wy + off), strokeWidth = 2.5f) }
        }
        val landY = h - 200f
        paint.textSize = 140f; paint.alpha = 210
        when (countryId) {
            "italy" -> { drawContext.canvas.nativeCanvas.drawText("🏟️", w * 0.12f, landY, paint); drawContext.canvas.nativeCanvas.drawText("🛶", w * 0.78f, waterY + 45f, paint.apply { textSize = 130f }) }
            "germany" -> { drawContext.canvas.nativeCanvas.drawText("🏰", w * 0.25f, landY - 60f, paint.apply { textSize = 170f }); drawContext.canvas.nativeCanvas.drawText("🏘️", w * 0.81f, landY, paint.apply { textSize = 120f }) }
            "japan" -> { drawContext.canvas.nativeCanvas.drawText("🏯", w * 0.20f, landY - 50f, paint.apply { textSize = 160f }); drawContext.canvas.nativeCanvas.drawText("⛩️", w * 0.82f, landY + 10f, paint.apply { textSize = 140f }) }
            "sudan" -> { drawContext.canvas.nativeCanvas.drawText("🔺", w * 0.30f, landY, paint.apply { textSize = 160f }); drawContext.canvas.nativeCanvas.drawText("🛖", w * 0.80f, landY, paint.apply { textSize = 130f }) }
        }
    }
}
