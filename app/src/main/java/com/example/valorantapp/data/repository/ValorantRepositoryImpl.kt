package com.example.valorantapp.data.repository

import com.canerture.valorantapp.data.model.maps.MapDetailResponse
import com.example.valorantapp.data.model.agents.AgentDetailResponse
import com.example.valorantapp.data.model.agents.AgentResponse
import com.example.valorantapp.data.model.competitivetiers.CompetitiveTiersResponse
import com.example.valorantapp.data.model.maps.MapsResponse
import com.example.valorantapp.data.model.weapons.WeaponDetailResponse
import com.example.valorantapp.data.model.weapons.WeaponsResponse
import com.example.valorantapp.data.remote.ValorantService
import com.example.valorantapp.domain.repository.ValorantRepository

class ValorantRepositoryImpl(private val valorantService: ValorantService) : ValorantRepository {
    override suspend fun getAgents(): AgentResponse = valorantService.getAgents()

    override suspend fun getMaps(): MapsResponse = valorantService.getMaps()

    override suspend fun getWeapons(): WeaponsResponse = valorantService.getWeapons()

    override suspend fun getCompetitiveTiers(): CompetitiveTiersResponse = valorantService.getCompetitiveTiers()
}