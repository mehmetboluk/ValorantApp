package com.example.valorantapp.domain.usecase.competitiveTiers

import com.example.valorantapp.core.util.Resource
import com.example.valorantapp.data.model.competitivetiers.CompetitiveTier
import com.example.valorantapp.data.model.competitivetiers.TierDto
import com.example.valorantapp.data.model.competitivetiers.toTier
import com.example.valorantapp.domain.model.Tier
import com.example.valorantapp.domain.repository.ValorantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CompetitiveTiersUseCase @Inject constructor(
    private val valorantRepository: ValorantRepository
) {
    operator fun invoke(): Flow<Resource<List<Tier>>> = flow{
        try {
            valorantRepository.getCompetitiveTiers().data?.last()?.tiers?.let { tiers ->
                val tiersTemp = arrayListOf<TierDto>()
                tiers.forEach { tier ->
                    if (tier.rankTriangleUpIcon != null) {
                        tiersTemp.add(tier)
                    }
                }
                emit(Resource.Success(tiersTemp.map { it.toTier() }))
            }
        }catch (e: IOException){
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        }
    }
}