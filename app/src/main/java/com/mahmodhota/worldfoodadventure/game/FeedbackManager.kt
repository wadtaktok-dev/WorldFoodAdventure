package com.mahmodhota.worldfoodadventure.game

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import org.json.JSONArray
import org.json.JSONObject
import java.util.UUID

class FeedbackManager(private val saveManager: SaveManager) {
    val history = mutableStateListOf<FeedbackEntry>()

    init {
        loadHistory()
    }

    private fun loadHistory() {
        // Try to load new JSON format first
        val jsonString = saveManager.getString("feedback_history_json", null)
        if (jsonString != null) {
            try {
                val array = JSONArray(jsonString)
                history.clear()
                for (i in 0 until array.length()) {
                    try {
                        val obj = array.getJSONObject(i)
                        history.add(FeedbackEntry(
                            id = obj.getString("id"),
                            rating = obj.getInt("rating"),
                            category = obj.getString("category"),
                            comment = obj.getString("comment"),
                            timestamp = obj.getLong("timestamp"),
                            screenshotPath = obj.optString("screenshot", "").let { if (it.isEmpty()) null else it }
                        ))
                    } catch (e: Exception) {
                        Log.e("FeedbackManager", "Skipping one corrupted JSON feedback entry", e)
                    }
                }
            } catch (e: Exception) {
                Log.e("FeedbackManager", "Major error parsing JSON feedback history", e)
            }
        } else {
            // No JSON found, try migrating legacy format
            migrateLegacy()
        }
        history.sortByDescending { it.timestamp }
    }

    private fun migrateLegacy() {
        val raw = saveManager.getStringSet("feedback_history", emptySet())
        if (raw.isNotEmpty()) {
            history.clear()
            raw.forEach { line ->
                try {
                    val parts = line.split("|")
                    if (parts.size >= 5) {
                        history.add(FeedbackEntry(
                            id = parts[0],
                            rating = parts[1].toInt(),
                            category = parts[2],
                            comment = parts[3].replace("\\n", "\n").replace("\\p", "|"),
                            timestamp = parts[4].toLong(),
                            screenshotPath = if (parts.size > 5 && parts[5].isNotEmpty()) parts[5] else null
                        ))
                    }
                } catch (e: Exception) {
                    Log.w("FeedbackManager", "Failed to migrate one legacy entry", e)
                }
            }
            saveAll()
            // Optional: clear legacy after successful migration to JSON
            // saveManager.setStringSet("feedback_history", emptySet())
        }
    }

    fun submitFeedback(rating: Int, category: String, comment: String): FeedbackEntry {
        val entry = FeedbackEntry(
            id = UUID.randomUUID().toString(),
            rating = rating,
            category = category,
            comment = comment,
            timestamp = System.currentTimeMillis()
        )
        history.add(0, entry)
        saveAll()
        return entry
    }

    private fun saveAll() {
        try {
            val array = JSONArray()
            history.forEach { entry ->
                val obj = JSONObject()
                obj.put("id", entry.id)
                obj.put("rating", entry.rating)
                obj.put("category", entry.category)
                obj.put("comment", entry.comment)
                obj.put("timestamp", entry.timestamp)
                obj.put("screenshot", entry.screenshotPath ?: "")
                array.put(obj)
            }
            saveManager.setString("feedback_history_json", array.toString())
        } catch (e: Exception) {
            Log.e("FeedbackManager", "Error saving feedback to JSON", e)
        }
    }

    fun exportFeedback(context: Context) {
        if (history.isEmpty()) return
        
        val sb = StringBuilder()
        sb.append("WorldFood Adventure - Feedback Export\n")
        sb.append("====================================\n\n")
        
        history.forEach { entry ->
            val date = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault())
                .format(java.util.Date(entry.timestamp))
            sb.append("Date: $date\n")
            sb.append("Rating: ${"⭐".repeat(entry.rating)}\n")
            sb.append("Category: ${entry.category}\n")
            sb.append("Comment: ${entry.comment}\n")
            sb.append("------------------------------------\n")
        }

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, sb.toString())
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "Share Feedback History")
        context.startActivity(shareIntent)
    }

    fun reset() {
        history.clear()
        saveManager.setString("feedback_history_json", "[]")
        saveManager.setStringSet("feedback_history", emptySet())
    }
}
