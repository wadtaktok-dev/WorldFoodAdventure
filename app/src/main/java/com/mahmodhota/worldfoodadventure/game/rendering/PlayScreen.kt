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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmodhota.worldfoodadventure.game.*

@Composable
fun PlayScreen(
    s: Int, 
    l: Int, 
    pX: Float, 
    cId: String, 
    skId: String, 
    items: List<GameItem>, 
    sub: String, 
    pa: Boolean, 
    bottomSafeZonePx: Float, 
    bottomInsetPx: Float, 
    onM: (Float) -> Unit, 
    onP: () -> Unit, 
    onR: () -> Unit
) {
    val ctx = LocalContext.current
    val density = LocalDensity.current
    val itemPaint = remember { Paint().apply { textSize = 80f; textAlign = Paint.Align.CENTER } }
    val skin = allSkins.find { it.id == skId } ?: allSkins[0]
    val dId = remember(skin.drawable) { safeDrawableId(skin.drawable, ctx) }
    val cBM = rememberSafeImageBitmap(dId, ctx)

    Box(modifier = Modifier.fillMaxSize()) {
        // --- FULL SCREEN GAMEPLAY CANVAS ---
        Canvas(Modifier.fillMaxSize()) {
            // UNIFIED POSITIONING (Bottom-Up)
            val cartGapPx = with(density) { CART_BOTTOM_GAP_DP.toPx() }
            val cartHeightPx = with(density) { PLAYER_CART_SIZE_DP.toPx() }
            
            val cartBottomY = size.height - bottomInsetPx - bottomSafeZonePx - cartGapPx
            val cartTopY = cartBottomY - cartHeightPx
            
            // Draw Cart
            if (cBM != null) {
                drawImage(
                    image = cBM, 
                    dstOffset = IntOffset((pX * size.width - (cartHeightPx / 2f)).toInt(), cartTopY.toInt()), 
                    dstSize = IntSize(cartHeightPx.toInt(), cartHeightPx.toInt())
                )
            } else {
                drawCircle(Color.Gray, cartHeightPx / 2.5f, Offset(pX * size.width, cartTopY + (cartHeightPx / 2f)))
            }

            // Draw Falling Items
            items.forEach { i -> 
                drawContext.canvas.nativeCanvas.drawText(i.emoji, i.x, i.y, itemPaint) 
            }
        }

        // --- OVERLAY: HEADER (Score & Pause) ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White.copy(0.7f))
                .statusBarsPadding()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Score: $s", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(sub, fontSize = 12.sp, fontWeight = FontWeight.Medium)
            }
            Row {
                IconButton(onClick = onR, modifier = Modifier.semantics { contentDescription = "Home" }) { 
                    Text("🏠", fontSize = 24.sp) 
                }
                IconButton(onClick = onP, modifier = Modifier.semantics { contentDescription = if (pa) "Resume" else "Pause" }) { 
                    Text(if (pa) "▶️" else "⏸️", fontSize = 24.sp) 
                } 
            }
        }

        // Positioned above the cart to avoid covering it
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = with(density) { (bottomSafeZonePx + bottomInsetPx).toDp() } + 170.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { onM(-0.15f) }, 
                    modifier = Modifier.size(120.dp, 70.dp), 
                    colors = ButtonDefaults.buttonColors(Color(0xFF2ECC71).copy(0.85f)), 
                    shape = RoundedCornerShape(16.dp)
                ) { 
                    Text("LEFT", fontWeight = FontWeight.Black) 
                }
                Button(
                    onClick = { onM(0.15f) }, 
                    modifier = Modifier.size(120.dp, 70.dp), 
                    colors = ButtonDefaults.buttonColors(Color(0xFF2ECC71).copy(0.85f)), 
                    shape = RoundedCornerShape(16.dp)
                ) { 
                    Text("RIGHT", fontWeight = FontWeight.Black) 
                }
            }
        }

        // --- OVERLAY: PAUSE STATE ---
        if (pa) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(0.5f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "PAUSED", 
                    color = Color.White, 
                    fontSize = 54.sp, 
                    fontWeight = FontWeight.Black,
                    letterSpacing = 4.sp
                )
            }
        }
    }
}
