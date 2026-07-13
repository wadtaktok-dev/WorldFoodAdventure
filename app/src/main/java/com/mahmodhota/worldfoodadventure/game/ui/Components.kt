package com.mahmodhota.worldfoodadventure.game.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmodhota.worldfoodadventure.game.ExperienceManager

@Composable
fun GameButton(txt: String, col: Color, onClick: () -> Unit, modifier: Modifier = Modifier, textColor: Color = Color.White) {
    Button(onClick = onClick, modifier = modifier.height(58.dp).widthIn(min = 200.dp), shape = RoundedCornerShape(16.dp), colors = ButtonDefaults.buttonColors(containerColor = col), elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)) { 
        Text(txt, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = textColor) 
    }
}

@Composable
fun XpBar(manager: ExperienceManager) {
    val progress by animateFloatAsState(targetValue = manager.getXpProgress(), label = "xpAnim")
    Column(modifier = Modifier.fillMaxWidth(0.85f), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(manager.lastUnlockedTitle, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text("Lvl ${manager.currentLevel}", color = Color(0xFFF1C40F), fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(6.dp))
        Box(modifier = Modifier.fillMaxWidth().height(12.dp).clip(RoundedCornerShape(6.dp)).background(Color.White.copy(0.2f))) {
            Box(modifier = Modifier.fillMaxWidth(progress).fillMaxHeight().background(Brush.horizontalGradient(listOf(Color(0xFFF1C40F), Color(0xFFE67E22)))))
        }
    }
}
