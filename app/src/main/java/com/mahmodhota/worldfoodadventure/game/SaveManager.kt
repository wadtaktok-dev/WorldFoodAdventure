package com.mahmodhota.worldfoodadventure.game

import android.content.Context
import android.content.SharedPreferences

class SaveManager(context: Context) {
    private val pref: SharedPreferences = context.getSharedPreferences("worldfood_prefs", Context.MODE_PRIVATE)
    var isWriteEnabled: Boolean = true

    fun getBoolean(key: String, default: Boolean): Boolean = pref.getBoolean(key, default)
    fun setBoolean(key: String, value: Boolean) {
        if (!isWriteEnabled) return
        pref.edit().putBoolean(key, value).apply()
    }

    fun getInt(key: String, default: Int): Int = pref.getInt(key, default)
    fun setInt(key: String, value: Int) {
        if (!isWriteEnabled) return
        pref.edit().putInt(key, value).apply()
    }

    fun getLong(key: String, default: Long): Long = pref.getLong(key, default)
    fun setLong(key: String, value: Long) {
        if (!isWriteEnabled) return
        pref.edit().putLong(key, value).apply()
    }

    fun getString(key: String, default: String?): String? = pref.getString(key, default)
    fun setString(key: String, value: String) {
        if (!isWriteEnabled) return
        pref.edit().putString(key, value).apply()
    }

    fun getStringSet(key: String, default: Set<String>): Set<String> = pref.getStringSet(key, default) ?: default
    fun setStringSet(key: String, value: Set<String>) {
        if (!isWriteEnabled) return
        pref.edit().putStringSet(key, value).apply()
    }

    fun clear() {
        if (!isWriteEnabled) return
        pref.edit().clear().apply()
    }
}
