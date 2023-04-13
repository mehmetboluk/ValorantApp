package com.example.valorantapp.presentation.screen.mapDetail

import com.example.valorantapp.domain.model.Map

data class MapDetailUIState(
    val isLoading: Boolean,
    val map: Map? = null
)
