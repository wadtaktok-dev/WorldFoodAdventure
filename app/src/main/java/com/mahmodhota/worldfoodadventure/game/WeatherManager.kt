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
                "greece", "spain", "turkey", "portugal" -> listOf(WeatherType.SUNNY, WeatherType.SUNSET, WeatherType.STARS, WeatherType.MORNING).random()
                "china", "india", "south_korea" -> listOf(WeatherType.SUNNY, WeatherType.CLOUDY, WeatherType.NIGHT, WeatherType.MORNING).random()
                "sudan" -> listOf(WeatherType.SUNNY, WeatherType.SUNSET, WeatherType.STARS, WeatherType.MORNING).random()
                "uk", "netherlands" -> listOf(WeatherType.RAIN, WeatherType.CLOUDY, WeatherType.MORNING).random()
                "austria", "switzerland" -> listOf(WeatherType.SNOW, WeatherType.CLOUDY, WeatherType.SUNNY, WeatherType.MORNING).random()
                "vietnam", "indonesia", "malaysia" -> listOf(WeatherType.SUNNY, WeatherType.RAIN, WeatherType.CLOUDY, WeatherType.SUNSET).random()
                "canada" -> listOf(WeatherType.SNOW, WeatherType.CLOUDY, WeatherType.RAIN, WeatherType.MORNING).random()
                "peru", "australia" -> listOf(WeatherType.SUNNY, WeatherType.SUNSET, WeatherType.STARS, WeatherType.MORNING).random()
                "chile", "argentina", "new_zealand" -> listOf(WeatherType.SUNNY, WeatherType.RAIN, WeatherType.SNOW, WeatherType.CLOUDY).random()
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
