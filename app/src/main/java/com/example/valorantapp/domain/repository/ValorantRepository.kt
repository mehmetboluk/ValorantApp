package com.example.valorantapp.domain.repository

import com.canerture.valorantapp.data.model.maps.MapDetailResponse
import com.example.valorantapp.data.model.agents.AgentDetailResponse
import com.example.valorantapp.data.model.agents.AgentResponse
import com.example.valorantapp.data.model.competitivetiers.CompetitiveTiersResponse
import com.example.valorantapp.data.model.maps.MapsResponse
import com.example.valorantapp.data.model.weapons.WeaponDetailResponse
import com.example.valorantapp.data.model.weapons.WeaponsResponse
import retrofit2.http.Path


interface ValorantRepository {
    suspend fun getAgents(): AgentResponse

    suspend fun getAgentByUuid(agentUuid: String): AgentDetailResponse

    suspend fun getMaps(): MapsResponse

    suspend fun getMapByUuid(mapUuid: String): MapDetailResponse

    suspend fun getWeapons(): WeaponsResponse

    suspend fun getWeaponByUuid(weaponUuid: String): WeaponDetailResponse

    suspend fun getCompetitiveTiers(): CompetitiveTiersResponse
}