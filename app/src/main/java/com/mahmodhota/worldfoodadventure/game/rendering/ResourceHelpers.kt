package com.mahmodhota.worldfoodadventure.game.rendering

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource

fun safeDrawableId(name: String, context: Context): Int {
    return try {
        context.resources.getIdentifier(name, "drawable", context.packageName)
    } catch (e: Exception) {
        0
    }
}

fun safeRawResourceId(name: String, context: Context): Int {
    return try {
        context.resources.getIdentifier(name, "raw", context.packageName)
    } catch (e: Exception) {
        0
    }
}

@Composable
fun rememberSafeImageBitmap(id: Int, context: Context): ImageBitmap? {
    return remember(id) {
        if (id != 0) {
            try {
                ImageBitmap.imageResource(context.resources, id)
            } catch (e: Exception) {
                null
            }
        } else {
            null
        }
    }
}
