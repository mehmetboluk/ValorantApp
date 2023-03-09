package com.example.valorantapp.presentation.screen.agents

import com.example.valorantapp.domain.model.Agent

data class AgentsUIState(
    val isLoading: Boolean,
    val items: List<Agent>? = null
)