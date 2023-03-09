package com.example.valorantapp.domain.model

import com.example.valorantapp.data.model.agents.Ability
import com.example.valorantapp.data.model.agents.Role
import java.io.Serializable

data class Agent(
    val abilities: List<Ability>,
    val description: String,
    val displayIcon: String,
    val displayName: String,
    val fullPortrait: String,
    val role: Role?,
    val uuid: String
): Serializable