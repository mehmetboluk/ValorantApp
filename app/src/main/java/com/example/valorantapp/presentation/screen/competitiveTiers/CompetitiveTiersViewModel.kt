package com.example.valorantapp.presentation.screen.competitiveTiers

import androidx.lifecycle.viewModelScope
import com.example.valorantapp.core.base.BaseViewModel
import com.example.valorantapp.core.util.Resource
import com.example.valorantapp.domain.usecase.competitiveTiers.CompetitiveTiersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompetitiveTiersViewModel @Inject constructor(
    private val competitiveTiersUseCase: CompetitiveTiersUseCase
): BaseViewModel() {
    private val _tiers: MutableStateFlow<CompetitiveTiersUIState> =
        MutableStateFlow(CompetitiveTiersUIState(false))
    val tiers: StateFlow<CompetitiveTiersUIState> get() = _tiers

    init {
        getTiers()
    }

    private fun getTiers(){
        viewModelScope.launch(Dispatchers.IO) {
            competitiveTiersUseCase().onEach { result ->
                when(result){
                    is Resource.Loading -> {
                        _tiers.value = tiers.value.copy(
                            isLoading = true,
                            data = null
                        )
                    }
                    is Resource.Error -> {
                        _tiers.value = tiers.value.copy(
                            isLoading = false,
                            data = null
                        )
                    }
                    is Resource.Success -> {
                        _tiers.value = tiers.value.copy(
                            isLoading = false,
                            data = result.data
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}