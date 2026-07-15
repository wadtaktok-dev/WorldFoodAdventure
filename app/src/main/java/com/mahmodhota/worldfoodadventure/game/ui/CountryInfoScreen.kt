package com.mahmodhota.worldfoodadventure.game.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmodhota.worldfoodadventure.game.CountryData
import com.mahmodhota.worldfoodadventure.game.rendering.safeDrawableId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryInfoScreen(
    country: CountryData,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onBack: () -> Unit,
    onPlay: () -> Unit
) {
    val ctx = LocalContext.current
    val bgResId = remember(country.id) { safeDrawableId("background_${country.id.lowercase()}", ctx) }
    val enc = country.encyclopedia

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Column {
                        Text(country.displayName, fontWeight = FontWeight.Black)
                        Text(country.nativeName, style = MaterialTheme.typography.labelMedium)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Text("⬅️", fontSize = 24.sp)
                    }
                },
                actions = {
                    IconButton(onClick = onToggleFavorite) {
                        Text(if (isFavorite) "❤️" else "🤍", fontSize = 24.sp)
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onPlay,
                icon = { Text("🎮") },
                text = { Text("PLAY MISSION") },
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Header Image
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                ) {
                    if (bgResId != 0) {
                        Image(
                            painter = painterResource(bgResId),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color.Transparent, Color.Black.copy(0.7f))
                                )
                            )
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(24.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(country.flagEmoji, fontSize = 60.sp)
                        Spacer(Modifier.width(16.dp))
                        Column {
                            Text(
                                country.capital,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                            Text("Capital City", color = Color.LightGray)
                        }
                    }
                }
            }

            // Quick Stats
            item {
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {

                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        StatColumn("Population", country.population)
                        StatColumn("Area", enc?.area ?: "N/A")
                        StatColumn("Currency", enc?.currency ?: "N/A")
                    }
                }
            }

            // General Info
            item {
                InfoSection(title = "Geography & Climate") {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        InfoRow("Continent", country.continent)
                        InfoRow("Region", country.region)
                        InfoRow("Climate", enc?.climate ?: "N/A")
                    }
                }
            }

            // Culture
            item {
                InfoSection(title = "National Symbols & Culture") {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        InfoRow("National Animal", enc?.nationalAnimal ?: "N/A")
                        InfoRow("National Flower", enc?.nationalFlower ?: "N/A")
                        InfoRow("Language", country.language)
                        InfoRow("Traditional Music", enc?.traditionalMusic ?: "N/A")
                    }
                }
            }

            // Food Gallery
            item {
                InfoSection(title = "Famous Foods") {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(bottom = 8.dp)
                    ) {
                        items(country.foods) { food ->
                            Card(
                                modifier = Modifier.width(150.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                            ) {
                                Column(
                                    modifier = Modifier.padding(12.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(food.emoji, fontSize = 40.sp)
                                    Text(
                                        food.name,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 1,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Landmarks
            if (country.landmarks.isNotEmpty()) {
                item {
                    InfoSection(title = "Must-Visit Landmarks") {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            country.landmarks.forEach { landmark ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
                                ) {
                                    Text(
                                        "📍 $landmark",
                                        modifier = Modifier.padding(12.dp),
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // History & Facts
            item {
                InfoSection(title = "History & Interesting Facts") {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Card(colors = CardDefaults.cardColors(containerColor = Color.White.copy(0.05f))) {
                            Column(Modifier.padding(16.dp)) {
                                Text("A Glimpse into History", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                Spacer(Modifier.height(8.dp))
                                Text(enc?.shortHistory ?: "Information coming soon.")
                            }
                        }
                        
                        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
                            Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                Text("💡", fontSize = 24.sp)
                                Spacer(Modifier.width(12.dp))
                                Text(country.fact, style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                }
            }

            // UNESCO Sites
            if (enc?.unescoSites?.isNotEmpty() == true) {
                item {
                    InfoSection(title = "UNESCO World Heritage Sites") {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            enc.unescoSites.forEach { site ->
                                Text("• $site", style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                }
            }

            // Travel Tips
            if (enc?.travelTips?.isNotEmpty() == true) {
                item {
                    InfoSection(title = "Travel Tips") {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            enc.travelTips.forEach { tip ->
                                Row(verticalAlignment = Alignment.Top) {
                                    Text("✅", modifier = Modifier.padding(top = 2.dp))
                                    Spacer(Modifier.width(8.dp))
                                    Text(tip, style = MaterialTheme.typography.bodyMedium)
                                }
                            }
                        }
                    }
                }
            }

            item {
                Spacer(Modifier.height(100.dp))
            }
        }
    }
}

@Composable
fun StatColumn(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary)
        Text(value, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun InfoSection(title: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            title.uppercase(),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Black,
            letterSpacing = 1.sp
        )
        Spacer(Modifier.height(12.dp))
        content()
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, fontWeight = FontWeight.Medium)
    }
}
