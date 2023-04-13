package com.example.valorantapp.presentation.screen.competitiveTiers

import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.valorantapp.core.base.BaseFragment
import com.example.valorantapp.databinding.FragmentCompetitiveTiersBinding
import com.example.valorantapp.presentation.adapter.CompetitiveTiersAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CompetitiveTiersFragment : BaseFragment<FragmentCompetitiveTiersBinding, CompetitiveTiersViewModel>(
    FragmentCompetitiveTiersBinding::inflate,CompetitiveTiersViewModel::class.java
) {

    private val competitiveTiersAdapter = CompetitiveTiersAdapter()

    override fun onViewCreateInvoke() {
        super.onViewCreateInvoke()

        setRecycler()
    }

    private fun setRecycler(){
        lifecycleScope.launch{
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.tiers.collectLatest {
                    when{
                        it.isLoading -> {
                            binding?.pbProgress?.isVisible = true
                        }
                        it.data.isNullOrEmpty().not() -> {
                            binding?.pbProgress?.isVisible = false
                            competitiveTiersAdapter.tiersList = it.data?.toList() ?: emptyList()
                        }
                    }
                }
            }
        }
        binding?.rvCompetitiveTiers?.apply {
            adapter = competitiveTiersAdapter
            layoutManager = GridLayoutManager(context,3)
        }
    }
}