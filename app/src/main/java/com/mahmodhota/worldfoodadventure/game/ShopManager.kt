package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ShopManager(private val saveManager: SaveManager) {
    var purchasedSkins by mutableStateOf(saveManager.getStringSet("skins", setOf("default")))
    var selectedSkinId by mutableStateOf(saveManager.getString("sel_skin", "default") ?: "default")

    fun selectSkin(skinId: String) {
        if (!saveManager.isWriteEnabled) return
        if (purchasedSkins.contains(skinId)) {
            selectedSkinId = skinId
            saveManager.setString("sel_skin", skinId)
        }
    }

    fun buySkin(skin: CartSkin, currentCoins: Int, onPurchase: (Int) -> Unit) {
        if (!saveManager.isWriteEnabled) return
        if (!purchasedSkins.contains(skin.id) && currentCoins >= skin.price) {
            val newCoins = currentCoins - skin.price
            purchasedSkins = purchasedSkins + skin.id
            saveManager.setStringSet("skins", purchasedSkins)
            onPurchase(newCoins)
        }
    }

    fun reset() {
        purchasedSkins = setOf("default")
        selectedSkinId = "default"
    }
}
