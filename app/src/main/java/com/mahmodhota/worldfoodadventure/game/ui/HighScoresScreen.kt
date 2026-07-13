package com.mahmodhota.worldfoodadventure.game.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HighScoresScreen(highScore: Int, onBack: () -> Unit) {
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) { 
        Text("HIGH SCORE", fontSize = 32.sp)
        Text("$highScore", fontSize = 80.sp, fontWeight = FontWeight.Black)
        Spacer(Modifier.height(40.dp))
        GameButton("BACK", Color.Gray, onBack) 
    }
}
