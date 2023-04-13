package com.example.valorantapp.domain.usecase.weapons

import com.example.valorantapp.core.util.Resource
import com.example.valorantapp.data.model.weapons.toWeapon
import com.example.valorantapp.domain.model.Weapon
import com.example.valorantapp.domain.repository.ValorantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetWeaponUseCase @Inject constructor(
    private val valorantRepository: ValorantRepository
) {
    operator fun invoke(): Flow<Resource<List<Weapon>>> = flow{
        try {
            valorantRepository.getWeapons().data?.map { it.toWeapon()}?.let {
                emit(Resource.Success(it))
            }
        }catch (e: IOException){
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        }
    }
}