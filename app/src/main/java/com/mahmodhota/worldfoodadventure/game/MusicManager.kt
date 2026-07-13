package com.mahmodhota.worldfoodadventure.game

import android.content.Context
import android.media.MediaPlayer

class MusicManager(private val context: Context) {
    private var mp: MediaPlayer? = null
    private var current: MusicStyle? = null
    var currentVolume = 1.0f

    fun play(style: MusicStyle, on: Boolean) {
        if (!on) { stop(); return }
        if (current == style) {
            mp?.let { if (!it.isPlaying) try { it.start() } catch (e: Exception) {} }
            return
        }
        stop()
        val id = context.resources.getIdentifier(style.fileName, "raw", context.packageName)
        if (id != 0) {
            try {
                mp = MediaPlayer.create(context, id)
                mp?.isLooping = true
                mp?.setVolume(currentVolume, currentVolume)
                mp?.start()
                current = style
            } catch (e: Exception) {}
        }
    }

    fun setVolume(v: Float) {
        currentVolume = v
        try { mp?.setVolume(v, v) } catch (e: Exception) {}
    }

    fun pause() { if (mp?.isPlaying == true) mp?.pause() }
    fun stop() { try { mp?.stop(); mp?.release() } catch (e: Exception) {}; mp = null; current = null }
    fun release() = stop()
}
