package com.mahmodhota.worldfoodadventure.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
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
fun MainMenuScreen(
    engine: GameEngine,
    rewardManager: RewardManager,
    expManager: ExperienceManager,
    worldProgressManager: WorldProgressManager,
    showcaseManager: LiveShowcaseManager,
    tvTravelManager: TvTravelManager,
    playerManager: PlayerManager,
    weatherManager: WeatherManager
) {
    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 40.dp), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("WorldFood", fontSize = 48.sp, fontWeight = FontWeight.Black, color = Color.White)
        Text("Adventure", fontSize = 24.sp, color = Color(0xFFF1C40F), fontWeight = FontWeight.Bold)
        
        Text(worldProgressManager.getExplorerTitle().uppercase(), color = Color(0xFF2ECC71), fontWeight = FontWeight.Black, fontSize = 12.sp)

        Spacer(modifier = Modifier.height(16.dp))
        XpBar(expManager)

        Spacer(modifier = Modifier.weight(1f))
        Text("AUTOMATIC MODE: ${if (showcaseManager.isRunning) "ON" else "OFF"}", color = if (showcaseManager.isRunning) Color(0xFF2ECC71) else Color.White.copy(0.6f), fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("🪙 ${rewardManager.coins}", fontSize = 22.sp, color = Color(0xFFF1C40F), fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            if (showcaseManager.isRunning) {
                GameButton("STOP AUTO", Color(0xFFE74C3C), { 
                    engine.stopShowcase(showcaseManager)
                }, modifier = Modifier.weight(1f))
            } else {
                GameButton("START AUTO", Color(0xFF9B59B6), { 
                    engine.playMode = PlayMode.LIVE_SHOWCASE
                    showcaseManager.start()
                    engine.countryId = "germany"
                    engine.setupLevel(engine.countryId, 5, weatherManager)
                }, modifier = Modifier.weight(1f))
            }
            GameButton("PLAY", Color(0xFF2ECC71), { 
                engine.playMode = PlayMode.NORMAL
                showcaseManager.stop()
                engine.setupLevel(engine.countryId, rewardManager.maxLives, weatherManager) 
            }, modifier = Modifier.weight(1f))
            IconButton(
                onClick = { engine.state = GameState.PROFILE },
                modifier = Modifier.size(54.dp).background(Color.White.copy(0.3f), RoundedCornerShape(12.dp))
            ) { 
                Text(playerManager.avatar, fontSize = 24.sp) 
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            GameButton("📺 TV MODE", Color(0xFF8E44AD), { 
                tvTravelManager.start()
                engine.state = GameState.TV_TRAVEL
            }, modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            GameButton("PASSPORT", Color(0xFF2980B9), { engine.state = GameState.PASSPORT }, modifier = Modifier.weight(1f))
            GameButton("MAP", Color(0xFFE67E22), { engine.state = GameState.WORLD_MAP }, modifier = Modifier.weight(1f))
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            GameButton("ALBUM", Color(0xFF27AE60), { engine.state = GameState.FOOD_ALBUM }, modifier = Modifier.weight(1f))
            GameButton("SHOP", Color(0xFFF1C40F), { engine.state = GameState.SHOP }, modifier = Modifier.weight(1f), textColor = Color.Black)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            GameButton("STATS", Color(0xFF3498DB), { engine.state = GameState.STATISTICS }, modifier = Modifier.weight(1f))
            GameButton("SETTINGS", Color(0xFF95A5A6), { engine.state = GameState.SETTINGS }, modifier = Modifier.weight(1f))
        }
    }
}
