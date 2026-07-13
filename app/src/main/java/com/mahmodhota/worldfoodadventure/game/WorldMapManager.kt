package com.mahmodhota.worldfoodadventure.game

class WorldMapManager(private val saveManager: SaveManager) {
    // This manager is now largely legacy or helper-based. 
    // WorldProgressManager is the canonical source of truth for completion.

    fun isUnlocked(countryId: String, progress: WorldProgressManager): Boolean {
        val country = CountryRepository.getCountry(countryId) ?: return false
        return progress.isUnlocked(country)
    }

    fun isCompleted(countryId: String, progress: WorldProgressManager): Boolean {
        return progress.isCompleted(countryId)
    }

    fun reset() {
        // Handled by manual reset in Settings or elsewhere if needed.
        // But for completeness:
        CountryRepository.allCountries.forEach { country ->
            saveManager.setBoolean("comp_${country.id}", false)
            saveManager.setBoolean("vis_${country.id}", false)
        }
    }
}
