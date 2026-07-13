package com.mahmodhota.worldfoodadventure.game

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun TvTravelScreen(
    manager: TvTravelManager,
    musicManager: MusicManager,
    ambientManager: AmbientSoundManager,
    environmentManager: EnvironmentManager,
    livingWorldManager: LivingWorldManager,
    npcManager: NpcManager,
    weatherManager: WeatherManager,
    onExit: () -> Unit
) {
    val country = manager.getCurrentCountry()
    
    // Safety: ensure write access is restored when leaving this screen
    DisposableEffect(Unit) {
        onDispose {
            manager.stop()
            ambientManager.stop()
        }
    }
    
    // Smooth music transition
    LaunchedEffect(country, manager.isMusicOn) {
        if (manager.isMusicOn) {
            val style = MusicStyle.entries.find { it.name.equals(country.id, ignoreCase = true) } 
                ?: MusicStyle.INTERNATIONAL
            musicManager.play(style, true)
        } else {
            musicManager.pause()
        }
    }

    LaunchedEffect(country) {
        weatherManager.pickWeatherForCountry(country)
    }

    LaunchedEffect(country, manager.isAmbientOn) {
        if (manager.isAmbientOn) {
            ambientManager.updateAmbient(country.id, true)
        } else {
            ambientManager.stop()
        }
    }

    LaunchedEffect(manager.isRunning, manager.isPaused) {
        while (manager.isRunning) {
            delay(1000)
            if (!manager.isPaused) {
                manager.updateTimer()
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Visual layers with Crossfade for the background
        Crossfade(targetState = country, animationSpec = tween(2000), label = "bg") { targetCountry ->
            Box(modifier = Modifier.fillMaxSize()) {
                BackgroundStyle(targetCountry.id, GameState.TV_TRAVEL)
                
                // Natural animations layers
                EnvironmentLayer(environmentManager)
                WildlifeLayer(livingWorldManager)
                NpcLayer(npcManager)
                
                // Weather overlay
                WeatherOverlay(weatherManager.currentGlobal, targetCountry)

                // Premium 2.0 Lighting
                val lightingColor = when(weatherManager.currentGlobal) {
                    WeatherType.MORNING -> Color(0xFFBBDEFB).copy(0.12f)
                    WeatherType.SUNSET -> Color(0xFFFFCC80).copy(0.18f)
                    WeatherType.NIGHT, WeatherType.STARS -> Color(0xFF1A237E).copy(0.28f)
                    WeatherType.CLOUDY, WeatherType.RAIN -> Color(0xFF90A4AE).copy(0.20f)
                    else -> Color.Transparent
                }
                Box(modifier = Modifier.fillMaxSize().background(lightingColor))
            }
        }

        // Information Panel - Non-animated, static position
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.Black.copy(0.6f)),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.fillMaxWidth(0.42f)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(country.flagEmoji, fontSize = 54.sp)
                        Spacer(Modifier.width(20.dp))
                        Column {
                            Text(country.displayName.uppercase(), fontSize = 32.sp, fontWeight = FontWeight.Black, color = Color.White)
                            Text(country.nativeName, fontSize = 18.sp, color = Color(0xFFF1C40F), fontWeight = FontWeight.Bold)
                        }
                    }
                    
                    Spacer(Modifier.height(20.dp))
                    Text("CAPITAL: ${country.capital}", color = Color.LightGray, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Text("CONTINENT: ${country.continent}", color = Color.LightGray, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    
                    Spacer(Modifier.height(20.dp))
                    Text("FAMOUS FOODS", color = Color(0xFFE67E22), fontWeight = FontWeight.Black, fontSize = 13.sp)
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        country.foods.take(4).forEach { Text(it.emoji, fontSize = 32.sp) }
                    }

                    Spacer(Modifier.height(20.dp))
                    Text("CULTURAL FACT", color = Color(0xFF2ECC71), fontWeight = FontWeight.Black, fontSize = 13.sp)
                    Text(country.fact, color = Color.White, fontSize = 16.sp, lineHeight = 24.sp)

                    if (country.landmarks.isNotEmpty()) {
                        Spacer(Modifier.height(20.dp))
                        Text("LANDMARKS", color = Color(0xFF3498DB), fontWeight = FontWeight.Black, fontSize = 13.sp)
                        Text(country.landmarks.joinToString(" • "), color = Color.LightGray, fontSize = 14.sp)
                    }
                }
            }
        }

        // Overlay Controls - Top Right
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.Black.copy(0.7f)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("📺 TV TRAVEL MODE", color = Color(0xFFF1C40F), fontWeight = FontWeight.Black, fontSize = 14.sp)
                    Text(country.displayName.uppercase(), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    
                    Spacer(Modifier.height(16.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        IconButton(onClick = { manager.previousCountry() }) { Text("⏮️", fontSize = 24.sp) }
                        IconButton(onClick = { if (manager.isPaused) manager.resume() else manager.pause() }) { 
                            Text(if (manager.isPaused) "▶️" else "⏸️", fontSize = 24.sp) 
                        }
                        IconButton(onClick = { manager.nextCountry() }) { Text("⏭️", fontSize = 24.sp) }
                    }

                    Spacer(Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(
                            onClick = { manager.isMusicOn = !manager.isMusicOn },
                            colors = ButtonDefaults.buttonColors(containerColor = if (manager.isMusicOn) Color(0xFF2ECC71) else Color.Gray),
                            modifier = Modifier.weight(1f).height(40.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) { Text("MUSIC", fontSize = 11.sp) }
                        
                        Button(
                            onClick = { manager.isAmbientOn = !manager.isAmbientOn },
                            colors = ButtonDefaults.buttonColors(containerColor = if (manager.isAmbientOn) Color(0xFF3498DB) else Color.Gray),
                            modifier = Modifier.weight(1f).height(40.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) { Text("AMBIENT", fontSize = 11.sp) }
                    }

                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = { manager.stop(); onExit() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE74C3C)),
                        modifier = Modifier.fillMaxWidth().height(44.dp)
                    ) { Text("EXIT TO MENU", fontWeight = FontWeight.ExtraBold) }
                }
            }
        }
    }
}
