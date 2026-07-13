package com.mahmodhota.worldfoodadventure.game.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
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
fun DailyChallengeScreen(
    dailyChallengeManager: DailyChallengeManager,
    engine: GameEngine,
    onBack: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("DAILY CHALLENGE", fontSize = 28.sp, fontWeight = FontWeight.Bold); Spacer(modifier = Modifier.height(16.dp))
        Card(modifier = Modifier.fillMaxWidth()) { 
            Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) { 
                Text(dailyChallengeManager.currentDaily.desc, fontSize = 20.sp)
                Text("${dailyChallengeManager.dailyProg} / ${dailyChallengeManager.currentDaily.target}", fontSize = 32.sp, fontWeight = FontWeight.Black, color = Color(0xFFE67E22))
                if (!dailyChallengeManager.dailyClaimed && dailyChallengeManager.dailyProg >= dailyChallengeManager.currentDaily.target) {
                    GameButton("CLAIM 25 🪙", Color(0xFFF1C40F), { 
                        engine.claimDailyRewardSafely()
                    }, textColor = Color.Black)
                } 
            } 
        }
        Spacer(modifier = Modifier.height(24.dp)); GameButton("BACK", Color.Gray, onBack)
    }
}
