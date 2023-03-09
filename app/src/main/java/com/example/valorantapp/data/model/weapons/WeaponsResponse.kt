package com.example.valorantapp.data.model.weapons

import com.google.gson.annotations.SerializedName

data class WeaponsResponse(
    @SerializedName("data")
    val data: List<WeaponDto>?,
    @SerializedName("status")
    val status: Int?
)