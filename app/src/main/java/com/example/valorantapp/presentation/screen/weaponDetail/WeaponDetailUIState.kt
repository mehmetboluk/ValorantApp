package com.example.valorantapp.presentation.screen.weaponDetail

import com.example.valorantapp.domain.model.Weapon

data class WeaponDetailUIState(
    val isLoading: Boolean,
    val weaponData: Weapon? = null
)