package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class WeatherManager {
    var currentByCountry by mutableStateOf<Map<String, WeatherType>>(emptyMap())
    var currentGlobal by mutableStateOf(WeatherType.SUNNY)

    fun pickWeatherForCountry(country: CountryData): WeatherType {
        val weather = if (country.weatherOptions.isNotEmpty()) {
            country.weatherOptions.random()
        } else {
            when(country.id) {
                "italy" -> listOf(WeatherType.SUNNY, WeatherType.MORNING, WeatherType.SUNSET, WeatherType.NIGHT).random()
                "germany" -> listOf(WeatherType.CLOUDY, WeatherType.RAIN, WeatherType.SNOW, WeatherType.MORNING).random()
                "japan" -> listOf(WeatherType.SAKURA, WeatherType.NIGHT, WeatherType.RAIN, WeatherType.MORNING).random()
                "france", "usa", "mexico" -> listOf(WeatherType.SUNNY, WeatherType.CLOUDY, WeatherType.SUNSET, WeatherType.MORNING).random()
                "brazil", "thailand" -> listOf(WeatherType.SUNNY, WeatherType.RAIN, WeatherType.SUNSET, WeatherType.MORNING).random()
                "greece", "spain", "turkey" -> listOf(WeatherType.SUNNY, WeatherType.SUNSET, WeatherType.STARS, WeatherType.MORNING).random()
                "china", "india" -> listOf(WeatherType.SUNNY, WeatherType.CLOUDY, WeatherType.NIGHT, WeatherType.MORNING).random()
                "sudan" -> listOf(WeatherType.SUNNY, WeatherType.SUNSET, WeatherType.STARS, WeatherType.MORNING).random()
                else -> listOf(WeatherType.SUNNY, WeatherType.CLOUDY, WeatherType.MORNING).random()
            }
        }
        currentGlobal = weather
        return weather
    }

    fun reset() {
        currentGlobal = WeatherType.SUNNY
    }
}
