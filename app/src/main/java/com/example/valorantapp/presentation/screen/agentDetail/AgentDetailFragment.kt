package com.example.valorantapp.presentation.screen.agentDetail

import android.annotation.SuppressLint
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.valorantapp.core.base.BaseFragment
import com.example.valorantapp.data.model.agents.Ability
import com.example.valorantapp.databinding.FragmentAgentDetailBinding
import com.example.valorantapp.domain.model.Agent
import com.example.valorantapp.presentation.adapter.AgentAbilityAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AgentDetailFragment : BaseFragment<FragmentAgentDetailBinding,AgentDetailViewModel>(
    FragmentAgentDetailBinding::inflate,AgentDetailViewModel::class.java
) {

    private var agent : Agent? = null
    private val navArgs : AgentDetailFragmentArgs by navArgs()
    private val agentAbilityAdapter = AgentAbilityAdapter(::clickRecyclerItem)

    override fun onViewCreateInvoke() {
        super.onViewCreateInvoke()

        setRecycler()
        init()

    }

    private fun init(){
        agent = navArgs.agent
        agent?.let {
            binding?.data = it
            binding?.ability = it.abilities[0]
            agentAbilityAdapter.abilityList = it.abilities
        }
    }

    private fun clickRecyclerItem(ability: Ability){
        binding?.ability = ability
    }

    private fun setRecycler(){
        binding?.rvAbilities?.let {
            it.apply {
                adapter = agentAbilityAdapter
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            }
        }
    }

}