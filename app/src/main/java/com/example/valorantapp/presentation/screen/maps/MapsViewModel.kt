package com.example.valorantapp.presentation.screen.maps

import androidx.lifecycle.viewModelScope
import com.example.valorantapp.core.base.BaseViewModel
import com.example.valorantapp.core.util.Resource
import com.example.valorantapp.domain.usecase.maps.GetMapsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val getMapsUseCase: GetMapsUseCase
): BaseViewModel() {
    private val _maps : MutableStateFlow<MapsUIState> =
        MutableStateFlow(MapsUIState(isLoading = true))
    val maps : StateFlow<MapsUIState> get() = _maps

    init {
        getMaps()
    }

    private fun getMaps() = viewModelScope.launch(Dispatchers.IO) {
        getMapsUseCase().onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _maps.value = maps.value.copy(
                        isLoading = true,
                        maps = null
                    )
                }
                is Resource.Error -> {
                    _maps.value = maps.value.copy(
                        isLoading = false,
                        maps = null
                    )
                }
                is Resource.Success -> {
                    _maps.value = maps.value.copy(
                        isLoading = false,
                        maps = result.data
                    )
                }
            }
        }
    }
}