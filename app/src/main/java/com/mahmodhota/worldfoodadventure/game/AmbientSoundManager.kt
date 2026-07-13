package com.mahmodhota.worldfoodadventure.game

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class AmbientSoundManager(private val context: Context, private val saveManager: SaveManager) {
    private var mp: MediaPlayer? = null
    private var currentCountryId: String? = null
    var volume by mutableStateOf(saveManager.getInt("amb_vol", 50) / 100f)

    fun updateAmbient(countryId: String?, on: Boolean) {
        if (!on || countryId == null) { stop(); return }
        if (currentCountryId == countryId) return

        stop()
        val resName = when(countryId) {
            "italy" -> "amb_italy"
            "germany" -> "amb_germany"
            "japan" -> "amb_japan"
            "sudan" -> "amb_sudan"
            "brazil" -> "amb_brazil"
            else -> "amb_nature"
        }

        val id = com.mahmodhota.worldfoodadventure.game.rendering.safeRawResourceId(resName, context)
        if (id != 0) {
            try {
                mp = MediaPlayer.create(context, id)
                mp?.isLooping = true
                mp?.setVolume(volume, volume)
                mp?.start()
                currentCountryId = countryId
            } catch (e: Exception) {}
        }
    }

    fun setAmbVolume(v: Float) {
        volume = v
        mp?.setVolume(v, v)
        saveManager.setInt("amb_vol", (v * 100).toInt())
    }

    fun stop() {
        try { mp?.stop(); mp?.release() } catch (e: Exception) {}
        mp = null
        currentCountryId = null
    }

    fun release() = stop()
}
