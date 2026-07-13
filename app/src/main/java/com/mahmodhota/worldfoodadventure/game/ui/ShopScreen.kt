package com.mahmodhota.worldfoodadventure.game.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmodhota.worldfoodadventure.game.*

@Composable
fun ShopScreen(
    rewardManager: RewardManager,
    shopManager: ShopManager,
    saveManager: SaveManager,
    onBack: () -> Unit
) {
    Column(Modifier.fillMaxSize().padding(24.dp), Arrangement.Center, Alignment.CenterHorizontally) {
        Text("SHOP", fontSize = 32.sp, fontWeight = FontWeight.Bold); Text("🪙 ${rewardManager.coins}", color = Color(0xFFF1C40F)); Spacer(Modifier.height(24.dp))
        allSkins.forEach { s ->
            val has = shopManager.purchasedSkins.contains(s.id); val isSel = shopManager.selectedSkinId == s.id
            Button({ if (has) { shopManager.selectSkin(s.id) } else { shopManager.buySkin(s, rewardManager.coins) { rewardManager.coins = it; saveManager.setInt("coins", it) } } }, Modifier.fillMaxWidth(0.8f).padding(4.dp), colors = ButtonDefaults.buttonColors(containerColor = if (isSel) Color(0xFF2ECC71) else Color.White, contentColor = Color.Black)) { Text("${s.name} ${if (isSel) "(SELECTED)" else if (has) "(OWNED)" else "🪙" + s.price}") }
        }
        Spacer(modifier = Modifier.height(32.dp)); GameButton("BACK", Color.Gray, onBack)
    }
}
