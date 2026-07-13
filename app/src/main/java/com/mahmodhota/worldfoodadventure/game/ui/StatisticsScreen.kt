package com.mahmodhota.worldfoodadventure.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmodhota.worldfoodadventure.game.StatisticsManager

@Composable
fun StatisticsScreen(manager: StatisticsManager, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF0F0F0F)).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("LIFETIME STATS", fontSize = 36.sp, fontWeight = FontWeight.Black, color = Color.White)
        Spacer(modifier = Modifier.height(24.dp))
        LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item { StatItem("Foods Harvested", manager.foodsCollected.toString(), Color(0xFF2ECC71)) }
            item { StatItem("Coins Collected", manager.coinsCollected.toString(), Color(0xFFF1C40F)) }
            item { StatItem("Bosses Slain", manager.bossesDefeated.toString(), Color(0xFFE74C3C)) }
        }
        Spacer(modifier = Modifier.height(24.dp))
        GameButton("CLOSE RECORDS", Color.Gray, onBack)
    }
}

@Composable
fun StatItem(label: String, value: String, color: Color) {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White.copy(0.05f)), shape = RoundedCornerShape(16.dp)) {
        Row(modifier = Modifier.padding(20.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(label, color = Color.LightGray, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Text(value, color = color, fontWeight = FontWeight.Black, fontSize = 20.sp)
        }
    }
}
