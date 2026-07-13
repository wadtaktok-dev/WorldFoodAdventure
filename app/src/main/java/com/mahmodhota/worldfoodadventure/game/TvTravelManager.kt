package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.*

class TvTravelManager(private val saveManager: SaveManager) {
    var isRunning by mutableStateOf(false)
    var isPaused by mutableStateOf(false)
    var currentCountryIndex by mutableIntStateOf(0)
    var timerSeconds by mutableIntStateOf(0)
    var isMusicOn by mutableStateOf(true)
    var isAmbientOn by mutableStateOf(true)

    private val playableCountries = CountryRepository.getPlayableCountries()

    fun start() {
        saveManager.isWriteEnabled = false // Safety protection
        isRunning = true
        isPaused = false
        currentCountryIndex = 0
        timerSeconds = 0
    }

    fun stop() {
        isRunning = false
        isPaused = false
        saveManager.isWriteEnabled = true
    }

    fun pause() {
        isPaused = true
    }

    fun resume() {
        isPaused = false
    }

    fun nextCountry() {
        currentCountryIndex = (currentCountryIndex + 1) % playableCountries.size
        timerSeconds = 0
    }

    fun previousCountry() {
        currentCountryIndex = if (currentCountryIndex <= 0) playableCountries.size - 1 else currentCountryIndex - 1
        timerSeconds = 0
    }

    fun updateTimer() {
        if (isRunning && !isPaused) {
            timerSeconds++
            if (timerSeconds >= 15) { // 12-20s range
                nextCountry()
            }
        }
    }

    fun getCurrentCountry(): CountryData {
        return playableCountries.getOrNull(currentCountryIndex) ?: CountryRepository.getFirstCountry()
    }

    fun reset() {
        stop()
        currentCountryIndex = 0
        timerSeconds = 0
    }
}
