package com.canerture.valorantapp.data.model.maps

import com.example.valorantapp.data.model.maps.MapDto
import com.google.gson.annotations.SerializedName

data class MapDetailResponse(
    @SerializedName("data")
    val data: MapDto?,
    @SerializedName("status")
    val status: Int?
)