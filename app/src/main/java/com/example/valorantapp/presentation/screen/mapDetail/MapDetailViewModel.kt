package com.example.valorantapp.presentation.screen.mapDetail

import androidx.lifecycle.viewModelScope
import com.example.valorantapp.core.base.BaseViewModel
import com.example.valorantapp.core.util.Resource
import com.example.valorantapp.domain.usecase.maps.GetMapDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapDetailViewModel @Inject constructor(
    private val getMapDetailUseCase: GetMapDetailUseCase
): BaseViewModel() {
    private val _getMapDetail : MutableStateFlow<MapDetailUIState> =
        MutableStateFlow(MapDetailUIState(isLoading = true))
    val getMapDetail: StateFlow<MapDetailUIState> get() = _getMapDetail

    fun getMapData(mapUUID: String) = viewModelScope.launch {
        getMapDetailUseCase(mapUUID).onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _getMapDetail.value = getMapDetail.value.copy(
                        isLoading = true,
                        null
                    )
                }
                is Resource.Error -> {
                    _getMapDetail.value = getMapDetail.value.copy(
                        isLoading = false,
                        null
                    )
                }
                is Resource.Success -> {
                    _getMapDetail.value = getMapDetail.value.copy(
                        isLoading = false,
                        result.data
                    )
                }
            }
        }.launchIn(this)
    }
}