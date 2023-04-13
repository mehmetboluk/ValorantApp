package com.example.valorantapp.domain.usecase.agents

import com.example.valorantapp.core.util.Resource
import com.example.valorantapp.data.model.agents.toAgent
import com.example.valorantapp.domain.model.Agent
import com.example.valorantapp.domain.repository.ValorantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAgentDetailUseCase @Inject constructor(
    private val valorantRepository: ValorantRepository
) {
    operator fun invoke(agentUuid: String) : Flow<Resource<Agent>> = flow {
        try {
            valorantRepository.getAgentByUuid(agentUuid).data?.toAgent()?.let {
                emit(Resource.Success(it))
            }
        }catch (e: IOException){
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        }
    }
}