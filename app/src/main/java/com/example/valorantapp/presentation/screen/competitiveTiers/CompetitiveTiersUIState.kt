package com.example.valorantapp.presentation.screen.competitiveTiers

import com.example.valorantapp.domain.model.Tier

data class CompetitiveTiersUIState(
    val isLoading: Boolean,
    val data: List<Tier>? = null
)