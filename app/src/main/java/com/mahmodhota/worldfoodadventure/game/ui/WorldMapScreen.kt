package com.mahmodhota.worldfoodadventure.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmodhota.worldfoodadventure.game.*

@Composable
fun WorldMapScreen(progress: WorldProgressManager, onBack: () -> Unit, onSelect: (CountryData) -> Unit) {
    Column(Modifier.fillMaxSize().background(Color(0xFF0D1B2A)).padding(16.dp)) {
        Text("SELECT DESTINATION", fontSize = 28.sp, fontWeight = FontWeight.Black, color = Color.White)
        LazyColumn(Modifier.weight(1f).fillMaxWidth()) {
            items(CountryRepository.allCountries) { c ->
                val isUnlocked = progress.isUnlocked(c) || progress.isCompleted(c.id)
                val isComingSoon = c.status == ContentStatus.COMING_SOON
                
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .alpha(if (isUnlocked && !isComingSoon) 1f else 0.5f)
                        .clickable(enabled = isUnlocked && !isComingSoon) { onSelect(c) },
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(0.1f))
                ) {
                    Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(if (isUnlocked && !isComingSoon) c.flagEmoji else "🔒", fontSize = 28.sp)
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(c.displayName, fontWeight = FontWeight.Bold, color = if (isUnlocked && !isComingSoon) Color.White else Color.Gray)
                            if (isComingSoon) {
                                Text("Coming Soon", fontSize = 12.sp, color = Color(0xFFF1C40F))
                            } else if (!isUnlocked) {
                                Text("Complete the previous country", fontSize = 12.sp, color = Color.LightGray)
                            } else if (progress.isCompleted(c.id)) {
                                Text("Mastered ⭐", fontSize = 12.sp, color = Color(0xFF2ECC71))
                            }
                        }
                    }
                }
            }
        }
        GameButton("BACK", Color.Gray, onBack)
    }
}
