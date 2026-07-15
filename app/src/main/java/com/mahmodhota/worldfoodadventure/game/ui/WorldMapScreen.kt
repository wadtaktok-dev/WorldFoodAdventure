package com.mahmodhota.worldfoodadventure.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mahmodhota.worldfoodadventure.game.*

@Composable
fun WorldMapScreen(
    progress: WorldProgressManager,
    onBack: () -> Unit,
    onPlay: (CountryData) -> Unit,
    onShowInfo: (CountryData) -> Unit,
    isFavorite: (String) -> Boolean,
    onToggleFavorite: (String) -> Unit
) {
    var selectedCountry by remember { mutableStateOf<CountryData?>(null) }

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
                        .clickable(
                            enabled = isUnlocked && !isComingSoon,
                            onClickLabel = "Select ${c.displayName}"
                        ) { selectedCountry = c },
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

    if (selectedCountry != null) {
        CountryOptionsDialog(
            country = selectedCountry!!,
            onDismiss = { selectedCountry = null },
            onPlay = { onPlay(selectedCountry!!); selectedCountry = null },
            onInfo = { onShowInfo(selectedCountry!!); selectedCountry = null },
            isFavorite = isFavorite(selectedCountry!!.id),
            onToggleFavorite = { onToggleFavorite(selectedCountry!!.id) }
        )
    }
}

@Composable
fun CountryOptionsDialog(
    country: CountryData,
    onDismiss: () -> Unit,
    onPlay: () -> Unit,
    onInfo: () -> Unit,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1B263B)),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(country.flagEmoji, fontSize = 60.sp)
                Spacer(Modifier.height(8.dp))
                Text(
                    country.displayName.uppercase(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
                Text(country.continent, color = Color.Gray, fontSize = 14.sp)
                
                Spacer(Modifier.height(24.dp))
                
                GameButton(
                    txt = "PLAY MISSION",
                    col = Color(0xFF2ECC71),
                    onClick = onPlay,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(Modifier.height(12.dp))
                
                GameButton(
                    txt = "INFORMATION",
                    col = Color(0xFF3498DB),
                    onClick = onInfo,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(Modifier.height(12.dp))
                
                OutlinedButton(
                    onClick = onToggleFavorite,
                    modifier = Modifier.fillMaxWidth().height(54.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
                ) {
                    Text(if (isFavorite) "❤️ UNFAVORITE" else "🤍 ADD TO FAVORITES")
                }
                
                Spacer(Modifier.height(16.dp))
                
                TextButton(onClick = onDismiss) {
                    Text("CLOSE", color = Color.Gray)
                }
            }
        }
    }
}
