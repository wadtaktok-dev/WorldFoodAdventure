package com.mahmodhota.worldfoodadventure.game.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmodhota.worldfoodadventure.game.*
import com.mahmodhota.worldfoodadventure.game.rendering.*
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
    
    DisposableEffect(Unit) {
        onDispose {
            manager.stop()
            ambientManager.stop()
        }
    }
    
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
        Crossfade(targetState = country, animationSpec = tween(2000), label = "bg") { targetCountry ->
            Box(modifier = Modifier.fillMaxSize()) {
                BackgroundStyle(targetCountry.id, GameState.TV_TRAVEL)
                EnvironmentLayer(environmentManager)
                WildlifeLayer(livingWorldManager)
                NpcLayer(npcManager)
                WeatherOverlay(weatherManager.currentGlobal, targetCountry)

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

        // REDESIGNED PREMIUM INFO PANEL
        TvPremiumInfoPanel(country)

        // RELOCATED CONTROLS - BOTTOM CENTER
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = BOTTOM_AD_SAFE_ZONE_DP + 16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Surface(
                color = Color.Black.copy(0.75f),
                shape = RoundedCornerShape(24.dp),
                border = BorderStroke(1.dp, Color.White.copy(0.15f))
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TvControlButton("PREV", "⏮️") { manager.previousCountry() }
                    
                    FloatingActionButton(
                        onClick = { if (manager.isPaused) manager.resume() else manager.pause() },
                        containerColor = Color(0xFFF1C40F),
                        contentColor = Color.Black,
                        shape = CircleShape,
                        modifier = Modifier.size(56.dp)
                    ) {
                        Text(if (manager.isPaused) "▶️" else "⏸️", fontSize = 28.sp)
                    }

                    TvControlButton("NEXT", "⏭️") { manager.nextCountry() }
                    
                    Box(modifier = Modifier.width(1.dp).height(32.dp).background(Color.White.copy(0.2f)))
                    
                    TvControlButton("EXIT", "❌", color = Color(0xFFE74C3C)) { onExit() }
                }
            }
        }
    }
}

@Composable
fun TvPremiumInfoPanel(country: CountryData) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
        Surface(
            modifier = Modifier
                .padding(start = 48.dp)
                .width(420.dp),
            color = Color.Black.copy(alpha = 0.82f),
            shape = RoundedCornerShape(32.dp),
            border = BorderStroke(1.dp, Color.White.copy(0.2f))
        ) {
            Column(modifier = Modifier.padding(32.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .shadow(8.dp, CircleShape)
                            .clip(CircleShape)
                            .background(Color.White.copy(0.1f))
                            .border(2.dp, Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(country.flagEmoji, fontSize = 52.sp)
                    }
                    Spacer(Modifier.width(24.dp))
                    Column {
                        Text(country.displayName.uppercase(), fontSize = 34.sp, fontWeight = FontWeight.Black, color = Color.White, lineHeight = 38.sp)
                        Text(country.nativeName, fontSize = 20.sp, color = Color(0xFFF1C40F), fontWeight = FontWeight.Bold)
                    }
                }
                
                Spacer(Modifier.height(32.dp))
                TvInfoRow("CAPITAL", country.capital)
                TvInfoRow("POPULATION", country.population)
                TvInfoRow("LANGUAGE", country.language)
                TvInfoRow("CURRENCY", country.encyclopedia?.currency ?: "N/A")
                TvInfoRow("CONTINENT", country.continent)
                TvInfoRow("CLIMATE", (country.encyclopedia?.climate ?: "Temperate").uppercase())

                Spacer(Modifier.height(24.dp))
                Text("CULTURAL HIGHLIGHT", color = Color(0xFF2ECC71), fontWeight = FontWeight.Black, fontSize = 14.sp, letterSpacing = 1.sp)
                Text(country.fact, color = Color.White, fontSize = 17.sp, lineHeight = 26.sp, fontWeight = FontWeight.Medium)

                if (country.foods.isNotEmpty()) {
                    Spacer(Modifier.height(24.dp))
                    Text("CURRENT DELICACY", color = Color(0xFFE67E22), fontWeight = FontWeight.Black, fontSize = 14.sp, letterSpacing = 1.sp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(country.foods.first().emoji, fontSize = 36.sp)
                        Spacer(Modifier.width(12.dp))
                        Text(country.foods.first().name.uppercase(), color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun TvInfoRow(label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 2.dp)) {
        Text("$label: ", color = Color.Gray, fontWeight = FontWeight.Black, fontSize = 13.sp, letterSpacing = 0.5.sp)
        Text(value.uppercase(), color = Color.LightGray, fontWeight = FontWeight.Bold, fontSize = 13.sp)
    }
}

@Composable
fun TvControlButton(text: String, emoji: String, color: Color = Color.White, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = color),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(emoji, fontSize = 24.sp)
            Text(text, fontSize = 10.sp, fontWeight = FontWeight.Black, color = color)
        }
    }
}
