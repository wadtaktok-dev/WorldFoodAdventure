package com.mahmodhota.worldfoodadventure.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmodhota.worldfoodadventure.game.*

@Composable
fun PassportScreen(progress: WorldProgressManager, album: FoodAlbumManager, stats: StatisticsManager, onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().background(Color(0xFF2C3E50)).padding(24.dp).safeDrawingPadding()) {
        Text("CHEF PASSPORT", fontSize = 36.sp, fontWeight = FontWeight.Black, color = Color.White)
        Text(progress.getExplorerTitle(), color = Color(0xFFF1C40F), fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(24.dp))
        
        LazyColumn(Modifier.weight(1f).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(CountryRepository.getPlayableCountries()) { country ->
                val isComp = progress.isCompleted(country.id)
                val cStats = progress.getCountryCompletionStats(country, album, stats)
                
                Card(
                    modifier = Modifier.fillMaxWidth().semantics(mergeDescendants = true) {
                        contentDescription = "${country.displayName}, ${if (isComp) "Stamped" else "Not completed yet"}"
                    },
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(0.1f))
                ) {
                    Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(80.dp).background(if (isComp) Color(0xFF27AE60) else Color.Gray, RoundedCornerShape(40.dp)), contentAlignment = Alignment.Center) {
                            if (isComp) Text("STAMPED", fontSize = 10.sp, color = Color.White, fontWeight = FontWeight.Black)
                            else Box(Modifier.alpha(0.3f)) { Text(country.flagEmoji, fontSize = 40.sp) }
                        }
                        Spacer(Modifier.width(16.dp))
                        Column {
                            Text(country.displayName, fontWeight = FontWeight.Bold, color = Color.White, fontSize = 20.sp)
                            Text("Completion: ${cStats.missionPercent}% | Foods: ${cStats.foodPercent}%", fontSize = 12.sp, color = Color.LightGray)
                            Row {
                                repeat(3) { i -> Text(if (i < cStats.stars) "⭐" else "☆", color = Color(0xFFF1C40F)) }
                            }
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        GameButton("BACK", Color.Gray, onBack)
    }
}
