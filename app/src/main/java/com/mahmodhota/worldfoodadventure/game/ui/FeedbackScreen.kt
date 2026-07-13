package com.mahmodhota.worldfoodadventure.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmodhota.worldfoodadventure.game.FeedbackManager

@Composable
fun FeedbackScreen(onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().background(Color(0xFF121212)).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("FEEDBACK", fontSize = 28.sp, fontWeight = FontWeight.Black, color = Color.White)
        Spacer(modifier = Modifier.weight(1f))
        GameButton("BACK", Color.Gray, onBack)
    }
}

@Composable
fun FeedbackHistoryScreen(onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().background(Color(0xFF121212)).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("HISTORY", fontSize = 28.sp, fontWeight = FontWeight.Black, color = Color.White)
        Spacer(modifier = Modifier.weight(1f))
        GameButton("BACK", Color.Gray, onBack)
    }
}
