package com.example.valorantapp.presentation.screen.weapons

import com.example.valorantapp.domain.model.Weapon

data class WeaponUIState(
    val isLoading: Boolean,
    val weaponList: List<Weapon>? = null
)