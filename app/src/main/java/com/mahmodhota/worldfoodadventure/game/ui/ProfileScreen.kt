package com.mahmodhota.worldfoodadventure.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
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
fun ProfileScreen(player: PlayerManager, exp: ExperienceManager, world: WorldProgressManager, album: FoodAlbumManager, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF121212)).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("CHEF PROFILE", fontSize = 36.sp, fontWeight = FontWeight.Black, color = Color.White)
        Spacer(modifier = Modifier.height(24.dp))
        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White.copy(0.05f)), shape = RoundedCornerShape(24.dp)) {
            Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(modifier = Modifier.size(100.dp), shape = RoundedCornerShape(50.dp), color = Color.White.copy(0.1f)) {
                    Box(contentAlignment = Alignment.Center) { Text(player.avatar, fontSize = 60.sp) }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(player.name, fontSize = 32.sp, fontWeight = FontWeight.Black, color = Color.White)
                Spacer(modifier = Modifier.height(12.dp))
                XpBar(exp)
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            item { ProfileStatRow("Current Rank", exp.lastUnlockedTitle, Color(0xFFF1C40F)) }
            item { ProfileStatRow("Countries Mastered", "${world.completionStatus.count { it.value }} / ${CountryRepository.getPlayableCountries().size}", Color(0xFF2ECC71)) }
            item { ProfileStatRow("Discoveries", "${album.discoveredFoods.size} / ${allFoods.size}", Color(0xFF3498DB)) }
        }
        Spacer(modifier = Modifier.height(16.dp))
        GameButton("BACK TO KITCHEN", Color.Gray, onBack)
    }
}

@Composable
fun ProfileStatRow(label: String, value: String, valueColor: Color) {
    Row(modifier = Modifier.fillMaxWidth().background(Color.White.copy(0.05f), RoundedCornerShape(12.dp)).padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(label, color = Color.Gray, fontWeight = FontWeight.Medium); Text(value, fontWeight = FontWeight.Bold, color = valueColor, fontSize = 18.sp)
    }
}
