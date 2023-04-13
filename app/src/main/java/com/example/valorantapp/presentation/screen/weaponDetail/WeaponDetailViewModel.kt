package com.example.valorantapp.presentation.screen.weaponDetail

import androidx.lifecycle.viewModelScope
import com.example.valorantapp.core.base.BaseViewModel
import com.example.valorantapp.core.util.Resource
import com.example.valorantapp.domain.usecase.weapons.GetWeaponDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeaponDetailViewModel @Inject constructor(
    private val getWeaponDetailUseCase: GetWeaponDetailUseCase
): BaseViewModel() {
    private val _weaponData: MutableStateFlow<WeaponDetailUIState> =
        MutableStateFlow(WeaponDetailUIState(isLoading = true))
    val weaponData: StateFlow<WeaponDetailUIState> get() = _weaponData

    fun getWeaponDetail(weaponUuid: String){
        viewModelScope.launch(Dispatchers.IO) {
            getWeaponDetailUseCase(weaponUuid).onEach { result ->
                when(result){
                    is Resource.Success -> {
                        _weaponData.value = weaponData.value.copy(
                            isLoading = false,
                            weaponData = result.data
                        )
                    }
                    is Resource.Error -> {
                        _weaponData.value = weaponData.value.copy(
                            isLoading = false,
                            weaponData = null
                        )
                    }
                    is Resource.Loading -> {
                        _weaponData.value = weaponData.value.copy(
                            isLoading = true,
                            weaponData = null
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}