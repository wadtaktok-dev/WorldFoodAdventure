package com.mahmodhota.worldfoodadventure.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class CountryStatusFilter { ALL, PLAYABLE, COMPLETED, UNLOCKED, COMING_SOON, FAVORITES, HOME }

class CountrySearchManager(private val repository: CountryRepository) {
    var searchQuery by mutableStateOf("")
    var selectedRegion by mutableStateOf("All")
    var statusFilter by mutableStateOf(CountryStatusFilter.ALL)
    
    val regions = listOf("All", "Europe", "Africa", "Asia", "Middle East", "North America", "South America", "Oceania")

    fun getFilteredCountries(progress: WorldProgressManager, homeland: PlayerHomelandManager): List<CountryData> {
        return repository.allCountries.filter { country ->
            val matchesSearch = country.displayName.contains(searchQuery, ignoreCase = true) || 
                               country.nativeName.contains(searchQuery, ignoreCase = true) ||
                               country.id.contains(searchQuery, ignoreCase = true)
            
            val matchesRegion = selectedRegion == "All" || country.region == selectedRegion
            
            val matchesStatus = when (statusFilter) {
                CountryStatusFilter.ALL -> true
                CountryStatusFilter.PLAYABLE -> country.status != ContentStatus.COMING_SOON
                CountryStatusFilter.COMPLETED -> progress.isCompleted(country.id)
                CountryStatusFilter.UNLOCKED -> progress.isUnlocked(country)
                CountryStatusFilter.COMING_SOON -> country.status == ContentStatus.COMING_SOON
                CountryStatusFilter.FAVORITES -> false // Placeholder for now
                CountryStatusFilter.HOME -> homeland.homeCountryId == country.id
            }
            
            matchesSearch && matchesRegion && matchesStatus
        }
    }
}
