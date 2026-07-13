package com.mahmodhota.worldfoodadventure.game.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun AchievementScreen(achievementManager: AchievementManager, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("TROPHIES", fontSize = 32.sp, fontWeight = FontWeight.Black); Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth()) { 
            items(items = allAchievements) { a -> 
                Card(modifier = Modifier.fillMaxWidth().padding(4.dp)) { 
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) { 
                        Text(if (achievementManager.unlockedAchMap[a.id]!=null) a.icon else "❓", fontSize = 32.sp)
                        Spacer(modifier = Modifier.width(16.dp))
                        Column { 
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(a.title, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("[${a.tier}]", fontSize = 10.sp, color = Color.Gray, fontWeight = FontWeight.Black)
                            }
                            Text(a.desc, fontSize = 12.sp, color = Color.Gray) 
                        } 
                    } 
                } 
            } 
        }
        GameButton("BACK", Color.Gray, onBack)
    }
}
