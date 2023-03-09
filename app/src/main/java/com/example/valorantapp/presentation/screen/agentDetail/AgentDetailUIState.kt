package com.example.valorantapp.presentation.screen.agentDetail

import com.example.valorantapp.domain.model.Agent

data class AgentDetailUIState(
    val isLoading: Boolean,
    val data: Agent? = null,
    val error: String? = null
)