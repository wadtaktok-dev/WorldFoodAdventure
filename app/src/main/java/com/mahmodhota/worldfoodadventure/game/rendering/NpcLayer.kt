package com.mahmodhota.worldfoodadventure.game.rendering

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.nativeCanvas
import com.mahmodhota.worldfoodadventure.game.NpcManager

@Composable
fun NpcLayer(manager: NpcManager) {
    val paint = remember { Paint().apply { textSize = 80f; textAlign = Paint.Align.CENTER } }
    Canvas(Modifier.fillMaxSize()) {
        try {
            for (i in manager.activeNpcs.indices) {
                val npc = manager.activeNpcs[i]
                if (!npc.x.isFinite() || !npc.y.isFinite()) continue
                drawContext.canvas.nativeCanvas.drawText(npc.emoji, npc.x, npc.y, paint)
            }
        } catch (e: Exception) { android.util.Log.e("WorldFood", "NpcLayer draw failed", e) }
    }
}
