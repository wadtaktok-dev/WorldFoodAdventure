package com.mahmodhota.worldfoodadventure.game.rendering

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.mahmodhota.worldfoodadventure.game.CountryData
import com.mahmodhota.worldfoodadventure.game.Season
import com.mahmodhota.worldfoodadventure.game.WeatherType
import kotlin.math.sin

@Composable
fun WeatherOverlay(weather: WeatherType, country: CountryData, season: Season = Season.NONE, festivalActive: Boolean = false) {
    val inf = rememberInfiniteTransition(label = "weather")
    val pOff by inf.animateFloat(0f, 1200f, infiniteRepeatable(tween(8000, easing = LinearEasing)), label = "p")
    val tTime by inf.animateFloat(0f, 100f, infiniteRepeatable(tween(200000, easing = LinearEasing)), label = "t")
    
    val morningColor = remember { Color(0xFFE3F2FD).copy(0.08f) }
    val rainColor = remember { Color(0xFF81D4FA).copy(0.45f) }
    val snowColor = remember { Color.White.copy(0.8f) }

    Canvas(Modifier.fillMaxSize()) {
        val w = size.width; val h = size.height
        when (weather) {
            WeatherType.MORNING -> { for (i in 0..8) { val rx = (i * (w / 8f) + pOff * 0.05f) % w; drawLine(morningColor, Offset(rx, 0f), Offset(rx + 200f, h), strokeWidth = 100f) } }
            WeatherType.RAIN -> { for (i in 0..100) { val x = (i * 73f + pOff * 0.3f) % w; val y = (i * 109f + pOff * 3.0f) % h; drawLine(rainColor, Offset(x, y), Offset(x - 3f, y + 40f), strokeWidth = 2.5f) } }
            WeatherType.SNOW -> { for (i in 0..40) { val x = (i * 97f + pOff * 0.1f + sin(tTime + i) * 50f) % w; val y = (i * 153f + pOff * 0.6f) % h; drawCircle(snowColor, if(i % 3 == 0) 6f else 4f, Offset(x, y)) } }
            else -> {}
        }
    }
}
