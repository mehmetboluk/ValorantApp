package com.example.valorantapp.presentation.screen.agentDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorantapp.core.util.Resource
import com.example.valorantapp.domain.usecase.agents.GetAgentDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentDetailViewModel @Inject constructor(
    private val getAgentDetailUseCase: GetAgentDetailUseCase
): ViewModel() {

    private val _getAgent : MutableStateFlow<AgentDetailUIState> =
        MutableStateFlow(AgentDetailUIState(isLoading = true))
    val getAgent : StateFlow<AgentDetailUIState> get() = _getAgent

    fun getAgentData(agentUuid: String) = viewModelScope.launch {
        getAgentDetailUseCase(agentUuid).onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _getAgent.value = getAgent.value.copy(
                        isLoading = true,
                        data = null
                    )
                }
                is Resource.Error -> {
                    _getAgent.value = getAgent.value.copy(
                        isLoading = false,
                        data = null
                    )
                }
                is Resource.Success -> {
                    _getAgent.value = getAgent.value.copy(
                        isLoading = false,
                        data = result.data
                    )
                }
            }
        }.launchIn(this)
    }
}