package com.mahmodhota.worldfoodadventure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.mahmodhota.worldfoodadventure.game.WorldFoodGame
import com.mahmodhota.worldfoodadventure.ui.theme.WorldFoodAdventureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorldFoodAdventureTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    WorldFoodGame()
                }
            }
        }
    }
}
