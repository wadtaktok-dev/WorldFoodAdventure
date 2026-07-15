package com.mahmodhota.worldfoodadventure.game

import androidx.compose.ui.geometry.Offset

enum class ContentStatus { FULL, STANDARD, COMING_SOON }

data class FoodData(
    val id: String, // e.g., "germany_brezel"
    val emoji: String,
    val name: String,
    val desc: String,
    val ingredients: List<String> = emptyList(),
    val nutrition: String = "",
    val isSpecial: Boolean = false,
    val isRare: Boolean = false
)

data class EncyclopediaData(
    val area: String,
    val currency: String,
    val climate: String,
    val nationalAnimal: String,
    val nationalFlower: String,
    val traditionalMusic: String,
    val shortHistory: String,
    val unescoSites: List<String> = emptyList(),
    val travelTips: List<String> = emptyList()
)

data class MissionConfig(
    val requiredFoods: Int = 10,
    val requiredSpecial: Int = 0,
    val requiredCoins: Int = 0,
    val maxBombsAllowed: Int = 999,
    val targetScore: Int = 0
)

data class CountryData(
    val id: String, // Stable unique ID: "germany", "sudan"
    val displayName: String,
    val nativeName: String,
    val flagEmoji: String,
    val isoCode: String,
    val region: String,
    val subregion: String,
    val continent: String,
    val language: String,
    val population: String,
    val capital: String,
    val welcomeMessage: String,
    val cultureDesc: String,
    val fact: String = "",
    val festival: String = "",
    val weatherOptions: List<WeatherType> = listOf(WeatherType.SUNNY, WeatherType.CLOUDY),
    val foods: List<FoodData>,
    val mission: MissionConfig,
    val landmarks: List<String> = emptyList(),
    val status: ContentStatus = ContentStatus.STANDARD,
    val mapLocation: Offset = Offset(0.5f, 0.5f),
    val customBackground: String? = null,
    val customMusic: String? = null,
    val customNpc: String? = null,
    val bossId: String? = null,
    val encyclopedia: EncyclopediaData? = null
)
