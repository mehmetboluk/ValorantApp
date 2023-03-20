package com.example.valorantapp.presentation.screen.weapons

import androidx.lifecycle.viewModelScope
import com.example.valorantapp.core.base.BaseViewModel
import com.example.valorantapp.core.util.Resource
import com.example.valorantapp.domain.usecase.weapons.GetWeaponUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeaponViewModel @Inject constructor(
    private val getWeaponUseCase: GetWeaponUseCase
): BaseViewModel() {

    private val _weapons: MutableStateFlow<WeaponUIState> =
        MutableStateFlow(WeaponUIState(isLoading = true))
    val weapons : StateFlow<WeaponUIState> get() = _weapons

    init {
        getWeapons()
    }

    private fun getWeapons(){
        viewModelScope.launch(Dispatchers.IO) {
            getWeaponUseCase().onEach { result ->
                when(result){
                    is Resource.Loading -> {
                        _weapons.value = weapons.value.copy(
                            isLoading = true,
                            weaponList = null
                        )
                    }
                    is Resource.Error -> {
                        _weapons.value = weapons.value.copy(
                            isLoading = false,
                            weaponList = null
                        )
                    }
                    is Resource.Success -> {
                        _weapons.value = weapons.value.copy(
                            isLoading = false,
                            weaponList = result.data
                        )
                    }
                }
            }
        }
    }
}