package com.example.valorantapp.presentation.screen.agents

import androidx.lifecycle.viewModelScope
import com.example.valorantapp.core.base.BaseViewModel
import com.example.valorantapp.core.util.Resource
import com.example.valorantapp.domain.usecase.agents.GetAgentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentsViewModel @Inject constructor(
    private val getAgentsUseCase: GetAgentsUseCase
) : BaseViewModel() {
    private val _agents : MutableStateFlow<AgentsUIState> =
        MutableStateFlow(AgentsUIState(isLoading = true))
    val agents : StateFlow<AgentsUIState>  get() = _agents

    init {
        getAgents()
    }

    fun getAgents(){
        viewModelScope.launch(Dispatchers.IO) {
            getAgentsUseCase().onEach { result ->
                when(result){
                    is Resource.Loading -> {
                        _agents.value = agents.value.copy(
                            isLoading = false,
                            items = null
                        )
                    }
                    is Resource.Error -> {
                        _agents.value = agents.value.copy(
                            isLoading = false,
                            items = null
                        )
                    }
                    is Resource.Success -> {
                        _agents.value = agents.value.copy(
                            isLoading = false,
                            items = result.data
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}