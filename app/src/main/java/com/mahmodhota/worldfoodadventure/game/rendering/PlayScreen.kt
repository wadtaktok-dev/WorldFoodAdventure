package com.mahmodhota.worldfoodadventure.game.rendering

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmodhota.worldfoodadventure.game.GameItem
import com.mahmodhota.worldfoodadventure.game.allSkins

@Composable
fun PlayScreen(s: Int, l: Int, pX: Float, cId: String, skId: String, items: List<GameItem>, sub: String, pa: Boolean, onM: (Float) -> Unit, onP: () -> Unit, onR: () -> Unit, onI: (Float, Float) -> Unit) {
    val ctx = LocalContext.current
    val itemPaint = remember { Paint().apply { textSize = 80f; textAlign = Paint.Align.CENTER } }
    val skin = allSkins.find { it.id == skId } ?: allSkins[0]
    val dId = remember(skin.drawable) { safeDrawableId(skin.drawable, ctx) }
    val cBM = rememberSafeImageBitmap(dId, ctx)
    BoxWithConstraints(Modifier.fillMaxSize()) {
        val w = maxWidth.value; val h = maxHeight.value
        LaunchedEffect(w, h) { onI(w, h) }
        Column {
            Row(Modifier.fillMaxWidth().background(Color.White.copy(0.7f)).padding(16.dp), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                Column { Text("Score: $s", fontWeight = FontWeight.Bold); Text(sub, fontSize = 12.sp) }
                Row { IconButton(onR) { Text("🏠", fontSize = 24.sp) }; IconButton(onP) { Text(if (pa) "▶️" else "⏸️", fontSize = 24.sp) } }
            }
            Box(Modifier.weight(1f).fillMaxWidth()) {
                Canvas(Modifier.fillMaxSize()) {
                    if (cBM != null) drawImage(cBM, dstOffset = IntOffset((pX * size.width - 75).toInt(), (size.height - 230).toInt()), dstSize = IntSize(150, 150))
                    else drawCircle(Color.Gray, 60f, Offset(pX * size.width, size.height - 150f))
                    items.forEach { i -> drawContext.canvas.nativeCanvas.drawText(i.emoji, i.x, i.y, itemPaint) }
                }
                if (pa) Box(Modifier.fillMaxSize().background(Color.Black.copy(0.4f)), Alignment.Center) { Text("PAUSED", color = Color.White, fontSize = 48.sp, fontWeight = FontWeight.Black) }
            }
            Row(Modifier.fillMaxWidth().padding(bottom = 40.dp, top = 20.dp), Arrangement.SpaceEvenly) {
                Button({ onM(-0.15f) }, Modifier.size(120.dp, 70.dp), colors = ButtonDefaults.buttonColors(Color(0xFF2ECC71)), shape = RoundedCornerShape(16.dp)) { Text("LEFT") }
                Button({ onM(0.15f) }, Modifier.size(120.dp, 70.dp), colors = ButtonDefaults.buttonColors(Color(0xFF2ECC71)), shape = RoundedCornerShape(16.dp)) { Text("RIGHT") }
            }
        }
    }
}
