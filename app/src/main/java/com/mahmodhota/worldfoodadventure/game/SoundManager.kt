package com.mahmodhota.worldfoodadventure.game

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import com.mahmodhota.worldfoodadventure.R

class SoundManager(context: Context) {
    private val pool = SoundPool.Builder()
        .setMaxStreams(10)
        .setAudioAttributes(AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build())
        .build()

    val collect = pool.load(context, R.raw.collect_food, 1)
    val hit = pool.load(context, R.raw.hit_obstacle, 1)
    val win = pool.load(context, R.raw.level_complete, 1)
    val lose = pool.load(context, R.raw.game_over, 1)
    val click = pool.load(context, R.raw.button_click, 1)
    
    // New Version 0.19 Sounds
    val chestOpen = pool.load(context, R.raw.collect_food, 1)
    val rewardPop = pool.load(context, R.raw.level_complete, 1)

    fun play(id: Int, enabled: Boolean) {
        if (enabled) pool.play(id, 1f, 1f, 0, 0, 1f)
    }
    fun release() = pool.release()
}

fun getDrawableId(name: String, context: Context): Int = context.resources.getIdentifier(name, "drawable", context.packageName)
