package com.example.valorantapp.presentation.screen.maps

import com.example.valorantapp.domain.model.Map

data class MapsUIState(
    val isLoading: Boolean,
    val maps: List<Map>? = null
)
