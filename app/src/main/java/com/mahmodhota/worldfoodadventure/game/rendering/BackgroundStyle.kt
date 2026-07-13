package com.mahmodhota.worldfoodadventure.game.rendering

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntSize
import com.mahmodhota.worldfoodadventure.game.GameState

@Composable
fun BackgroundStyle(cId: String?, state: GameState) {
    val ctx = LocalContext.current
    val fallbackBrush = remember { Brush.verticalGradient(listOf(Color(0xFF1A237E), Color(0xFF4DB6AC))) }
    val id = remember(cId, state) { 
        if (state == GameState.MENU) safeDrawableId("background_menu_cinematic", ctx)
        else cId?.let { safeDrawableId("background_${it.lowercase()}", ctx) } ?: 0 
    }
    val bm = rememberSafeImageBitmap(id, ctx)
    Canvas(Modifier.fillMaxSize()) {
        if (bm != null) drawImage(bm, dstSize = IntSize(size.width.toInt(), size.height.toInt())) 
        else drawRect(fallbackBrush)
    }
}
