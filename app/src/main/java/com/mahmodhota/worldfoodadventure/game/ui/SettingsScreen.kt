package com.mahmodhota.worldfoodadventure.game.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmodhota.worldfoodadventure.game.*

@Composable
fun SettingsScreen(
    sOn: Boolean,
    onSOnChanged: (Boolean) -> Unit,
    mOn: Boolean,
    onMOnChanged: (Boolean) -> Unit,
    mSt: MusicStyle,
    onMStChanged: (MusicStyle) -> Unit,
    ambientSoundManager: AmbientSoundManager,
    onResetProgress: () -> Unit,
    onBack: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("SETTINGS", fontSize = 32.sp, fontWeight = FontWeight.Bold); Spacer(modifier = Modifier.height(24.dp))
        Row(modifier = Modifier.fillMaxWidth(0.8f), horizontalArrangement = Arrangement.SpaceBetween, Alignment.CenterVertically) { 
            Text("SFX")
            Switch(sOn, onSOnChanged) 
        }
        Row(modifier = Modifier.fillMaxWidth(0.8f), horizontalArrangement = Arrangement.SpaceBetween, Alignment.CenterVertically) { 
            Text("Music")
            Switch(mOn, onMOnChanged) 
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        Text("Ambient Volume", fontWeight = FontWeight.Bold)
        Slider(value = ambientSoundManager.volume, onValueChange = { ambientSoundManager.setAmbVolume(it) }, modifier = Modifier.fillMaxWidth(0.8f))

        Spacer(modifier = Modifier.height(16.dp)); Text("Music Style", fontWeight = FontWeight.Bold)
        LazyColumn(modifier = Modifier.height(180.dp).fillMaxWidth(0.8f)) { 
            items(items = MusicStyle.entries) { s -> 
                Row(
                    modifier = Modifier.fillMaxWidth().clickable(
                        onClickLabel = "Change music style to ${s.displayName}"
                    ) { onMStChanged(s) }.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(mSt == s, null); Text(s.displayName, modifier = Modifier.padding(start = 8.dp)) 
                } 
            } 
        }
        Spacer(modifier = Modifier.height(16.dp)); OutlinedButton(onClick = onResetProgress, Modifier.fillMaxWidth(0.8f)) { 
            Text("Reset Progress", color = Color.Red) 
        }
        Spacer(modifier = Modifier.weight(1f)); GameButton("BACK", Color.Gray, onBack)
    }
}
