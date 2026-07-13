package com.mahmodhota.worldfoodadventure.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmodhota.worldfoodadventure.game.CountryData
import kotlinx.coroutines.delay

@Composable
fun CountryIntroCard(country: CountryData, onDismiss: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(4000)
        onDismiss()
    }
    Box(Modifier.fillMaxSize().background(Color.Black.copy(0.7f)).clickable { onDismiss() }, Alignment.Center) {
        Card(modifier = Modifier.fillMaxWidth(0.85f), colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(24.dp), elevation = CardDefaults.cardElevation(12.dp)) {
            Column(Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(country.flagEmoji, fontSize = 70.sp)
                Text(country.displayName.uppercase(), fontSize = 32.sp, fontWeight = FontWeight.Black, color = Color.Black)
                Text(country.continent.uppercase(), fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                
                Spacer(Modifier.height(16.dp))
                DiscoveryInfoRow("Capital", country.capital)
                DiscoveryInfoRow("Language", country.language)
                DiscoveryInfoRow("Population", country.population)
                
                Spacer(Modifier.height(20.dp))
                Text("FAMOUS FOODS", fontSize = 14.sp, fontWeight = FontWeight.Black, color = Color(0xFFE67E22))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    country.foods.take(3).forEach { Text(it.emoji, fontSize = 32.sp) }
                }

                Spacer(Modifier.height(24.dp))
                Text(country.welcomeMessage, fontSize = 18.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic, textAlign = TextAlign.Center, color = Color(0xFF2E7D32))
            }
        }
    }
}

@Composable
fun DiscoveryInfoRow(label: String, value: String) {
    Row(Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, fontWeight = FontWeight.Bold, color = Color.DarkGray, fontSize = 14.sp)
        Text(value, color = Color.Black, fontSize = 14.sp)
    }
}

@Composable
fun DidYouKnowCard(country: CountryData, onDismiss: () -> Unit) {
    Box(Modifier.fillMaxSize().background(Color.Black.copy(0.7f)).clickable { onDismiss() }, Alignment.Center) {
        Card(modifier = Modifier.fillMaxWidth(0.8f), colors = CardDefaults.cardColors(containerColor = Color(0xFFF1C40F)), shape = RoundedCornerShape(24.dp)) {
            Column(Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("💡 DID YOU KNOW?", fontWeight = FontWeight.Black, fontSize = 22.sp, color = Color.Black)
                Spacer(Modifier.height(16.dp))
                Text(country.fact, fontSize = 18.sp, textAlign = TextAlign.Center, color = Color.Black, fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(24.dp))
                GameButton("AWESOME!", Color.Black, onDismiss)
            }
        }
    }
}
