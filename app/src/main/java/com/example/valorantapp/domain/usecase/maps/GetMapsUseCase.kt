package com.example.valorantapp.domain.usecase.maps

import com.example.valorantapp.core.util.Resource
import com.example.valorantapp.data.model.maps.toMap
import com.example.valorantapp.domain.model.Map
import com.example.valorantapp.domain.repository.ValorantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMapsUseCase @Inject constructor(
    private val valorantRepository: ValorantRepository
) {
    operator fun invoke(): Flow<Resource<List<Map>>> = flow {
        try {
            valorantRepository.getMaps().data?.map { it.toMap()}?.let {
                emit(Resource.Success(it))
            }
        }catch (e: IOException){
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        }
    }
}