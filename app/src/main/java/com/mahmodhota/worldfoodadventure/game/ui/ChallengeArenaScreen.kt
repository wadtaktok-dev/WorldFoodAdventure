package com.mahmodhota.worldfoodadventure.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmodhota.worldfoodadventure.game.*

@Composable
fun ChallengeArenaScreen(onBack: () -> Unit, onStart: (CountryData) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF2C3E50)).padding(24.dp)) {
        Text("CHALLENGE ARENA", fontSize = 32.sp, fontWeight = FontWeight.Black, color = Color.White)
        LazyColumn(Modifier.weight(1f)) {
            items(CountryRepository.getPlayableCountries()) { country ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable { onStart(country) }) {
                    Row(Modifier.padding(16.dp)) { Text(country.flagEmoji); Spacer(Modifier.width(16.dp)); Text(country.displayName) }
                }
            }
        }
        GameButton("BACK", Color.Gray, onBack)
    }
}
