package com.example.valorantapp.presentation.screen.agents

import android.annotation.SuppressLint
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.valorantapp.R
import com.example.valorantapp.core.base.BaseFragment
import com.example.valorantapp.databinding.FragmentAgentsBinding
import com.example.valorantapp.domain.model.Agent
import com.example.valorantapp.presentation.adapter.AgentsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AgentsFragment : BaseFragment<FragmentAgentsBinding, AgentsViewModel>(
    FragmentAgentsBinding::inflate, AgentsViewModel::class.java
) {

    private lateinit var agentsAdapter : AgentsAdapter

    override fun onViewCreateInvoke() {
        super.onViewCreateInvoke()

        agentsAdapter = AgentsAdapter(::adapterClick)
        setRecycler()
        observer()

    }

    private fun setRecycler() {

        binding?.rvAgents?.apply {
            adapter = agentsAdapter
            layoutManager = GridLayoutManager(context,3)
        }
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    private fun observer(){
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.agents.collectLatest{
                    when{
                        it.isLoading -> {
                            binding?.pbProgress?.isVisible = true
                        }
                        it.items.isNullOrEmpty().not() -> {
                            binding?.pbProgress?.isVisible = false
                            agentsAdapter.agentList = it.items ?: emptyList()
                        }
                    }
                }
            }
        }
    }

    private fun adapterClick(agent: Agent ){
        val action = AgentsFragmentDirections.actionAgentsFragmentToAgentDetailFragment(agent)
        navController?.navigate(action)
    }

}