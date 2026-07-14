package com.mahmodhota.worldfoodadventure.game.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmodhota.worldfoodadventure.game.*

@Composable
fun ShowcaseOverlay(
    showcaseManager: LiveShowcaseManager,
    engine: GameEngine,
    musicEnabled: Boolean,
    weatherManager: WeatherManager,
    onMusicChanged: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.Black.copy(0.7f)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            "LIVE SHOWCASE",
                            color = Color(0xFFF1C40F),
                            fontWeight = FontWeight.Black,
                            fontSize = 14.sp
                        )
                        Text(
                            engine.currentCountry.displayName.uppercase(),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        IconButton(onClick = {
                            val prev = CountryRepository.getPreviousCountry(engine.countryId)
                            engine.previousCountryId = engine.countryId
                            engine.countryId = prev.id
                            engine.state = GameState.WORLD_TRAVEL
                        }, modifier = Modifier.semantics { contentDescription = "Previous Country" }) {
                            Text("⏮️", fontSize = 20.sp)
                        }

                        IconButton(onClick = {
                            if (showcaseManager.isPaused) showcaseManager.resume()
                            else showcaseManager.pause()
                        }, modifier = Modifier.semantics { contentDescription = if (showcaseManager.isPaused) "Resume" else "Pause" }) {
                            Text(if (showcaseManager.isPaused) "▶️" else "⏸️", fontSize = 20.sp)
                        }

                        IconButton(onClick = {
                            val next = CountryRepository.getNextCountry(engine.countryId)
                            engine.previousCountryId = engine.countryId
                            engine.countryId = next.id
                            engine.state = GameState.WORLD_TRAVEL
                        }, modifier = Modifier.semantics { contentDescription = "Next Country" }) {
                            Text("⏭️", fontSize = 20.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    GameButton(
                        txt = if (musicEnabled) "MUSIC: ON" else "MUSIC: OFF",
                        col = if (musicEnabled) Color(0xFF2ECC71) else Color.Gray,
                        onClick = { onMusicChanged(!musicEnabled) },
                        modifier = Modifier.weight(1f),
                        textColor = Color.White
                    )

                    GameButton(
                        txt = "STOP AUTO",
                        col = Color(0xFFE74C3C),
                        onClick = {
                            engine.stopShowcase(showcaseManager)
                        },
                        modifier = Modifier.weight(1f),
                        textColor = Color.White
                    )
                }
            }
        }
    }
}
