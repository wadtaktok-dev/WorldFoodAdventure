package com.mahmodhota.worldfoodadventure.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmodhota.worldfoodadventure.game.*
import kotlinx.coroutines.delay

@Composable
fun LevelUpScreen(manager: ExperienceManager, onContinue: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(Brush.radialGradient(listOf(Color(0xFF1E3C72), Color(0xFF2A5298)))), Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("✨ RANK UP! ✨", color = Color(0xFFF1C40F), fontSize = 54.sp, fontWeight = FontWeight.Black)
            Text(manager.lastUnlockedTitle.uppercase(), color = Color(0xFF2ECC71), fontSize = 42.sp, fontWeight = FontWeight.Black)
            Spacer(modifier = Modifier.height(60.dp))
            GameButton("CONTINUE JOURNEY", Color(0xFF2ECC71), onContinue)
        }
    }
}

@Composable
fun GalleryMenuScreen(onNavigate: (GameState) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF1A1A2E)).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("WORLD GALLERY", fontSize = 36.sp, fontWeight = FontWeight.Black, color = Color.White)
        Spacer(modifier = Modifier.weight(1f))
        GameButton("BACK TO MENU", Color.Gray, { onNavigate(GameState.MENU) })
    }
}

@Composable
fun DailyLoginScreen(manager: DailyLoginManager, rewards: RewardManager, shop: ShopManager, sounds: SoundManager, sOn: Boolean, onDone: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("DAILY REWARDS", fontSize = 36.sp, fontWeight = FontWeight.Black)
        Spacer(modifier = Modifier.weight(1f))
        GameButton("CONTINUE", Color.White, onDone, textColor = Color.Black)
    }
}

@Composable
fun SeasonalEventsScreen(season: SeasonManager, events: EventManager, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("SEASONAL EVENTS", fontSize = 32.sp, fontWeight = FontWeight.Black)
        Spacer(modifier = Modifier.weight(1f))
        GameButton("BACK", Color.Gray, onBack)
    }
}

@Composable
fun BossIntroScreen(countryId: String, onStart: () -> Unit) {
    Box(Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("⚠️ BOSS ALERT ⚠️", color = Color.Red, fontSize = 48.sp, fontWeight = FontWeight.Black)
            Spacer(Modifier.height(60.dp))
            GameButton("ENGAGE BATTLE!", Color.Red, onStart)
        }
    }
}

@Composable
fun BossBattleScreen(engine: GameEngine, onHome: () -> Unit, onPause: () -> Unit) {
    Column(Modifier.fillMaxSize().background(Color.Black.copy(0.4f))) {
        Row(Modifier.fillMaxWidth().padding(16.dp), Arrangement.SpaceBetween) {
            IconButton(onHome) { Text("🏠", fontSize = 24.sp) }
            IconButton(onPause) { Text("⏸️", fontSize = 24.sp) }
        }
        Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text("BOSS BATTLE", color = Color.White, fontSize = 40.sp, fontWeight = FontWeight.Black)
        }
    }
}

@Composable
fun WorldTravelScreen(from: CountryData, to: CountryData, visited: Boolean, onDone: () -> Unit) {
    LaunchedEffect(Unit) { delay(2500); onDone() }
    Box(Modifier.fillMaxSize().background(Color(0xFF0D1B2A)), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(to.flagEmoji, fontSize = 80.sp)
            Spacer(Modifier.height(16.dp))
            Text("TRAVELING TO ${to.displayName.uppercase()}...", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun TreasureChestScreen(reward: Reward?, sounds: SoundManager, on: Boolean, isChallenge: Boolean = false, onDone: () -> Unit) {
    Box(Modifier.fillMaxSize().background(Color.Black.copy(0.85f)), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("TREASURE FOUND!", fontSize = 36.sp, color = Color(0xFFF1C40F), fontWeight = FontWeight.Black)
            Spacer(Modifier.height(80.dp))
            Text("🎁", fontSize = 100.sp)
            Spacer(Modifier.height(80.dp))
            GameButton("COLLECT", Color(0xFF2ECC71), onDone) 
        }
    }
}
