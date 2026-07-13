package com.mahmodhota.worldfoodadventure.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmodhota.worldfoodadventure.game.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun FoodAlbumScreen(manager: FoodAlbumManager, onBack: () -> Unit) {
    val sdf = remember { SimpleDateFormat("dd MMM yyyy", Locale.getDefault()) }
    
    Column(Modifier.fillMaxSize().background(Color(0xFF1E1E1E)).padding(16.dp).safeDrawingPadding()) {
        Text("FOOD ALBUM", fontSize = 32.sp, fontWeight = FontWeight.Black, color = Color.White)
        Text("${manager.discoveredFoods.size} / ${allFoods.size} DISCOVERED", color = Color(0xFFF1C40F), fontWeight = FontWeight.Bold)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn(Modifier.weight(1f).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            val countries = CountryRepository.allCountries.filter { it.status != ContentStatus.COMING_SOON }
            items(countries) { country ->
                Column(Modifier.fillMaxWidth()) {
                    Text(country.displayName.uppercase(), color = Color.Gray, fontWeight = FontWeight.Black, fontSize = 12.sp)
                    Spacer(Modifier.height(8.dp))
                    
                    val countryFoods = allFoods.filter { it.countryId == country.id }
                    countryFoods.forEach { food ->
                        val isDiscovered = manager.discoveredFoods.contains(food.id)
                        val date = manager.discoveryDates[food.id]
                        
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = if (isDiscovered) Color.White.copy(0.1f) else Color.White.copy(0.02f))
                        ) {
                            Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                                Text(if (isDiscovered) food.emoji else "🔒", fontSize = 32.sp)
                                Spacer(Modifier.width(16.dp))
                                Column(Modifier.weight(1f)) {
                                    Text(
                                        text = if (isDiscovered) food.name else "???",
                                        fontWeight = FontWeight.Bold,
                                        color = if (isDiscovered) Color.White else Color.Gray
                                    )
                                    if (isDiscovered) {
                                        Text(food.desc, fontSize = 12.sp, color = Color.LightGray)
                                        Text(
                                            "Discovered: ${date?.let { sdf.format(Date(it)) } ?: "Unknown"}",
                                            fontSize = 10.sp,
                                            color = Color(0xFF2ECC71)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    HorizontalDivider(color = Color.White.copy(0.1f))
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        GameButton("BACK", Color.Gray, onBack)
    }
}
