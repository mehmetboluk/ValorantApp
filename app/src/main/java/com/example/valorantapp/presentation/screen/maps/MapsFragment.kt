package com.example.valorantapp.presentation.screen.maps

import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.valorantapp.core.base.BaseFragment
import com.example.valorantapp.databinding.FragmentMapsBinding
import com.example.valorantapp.domain.model.Map
import com.example.valorantapp.presentation.adapter.MapsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapsFragment : BaseFragment<FragmentMapsBinding,MapsViewModel>(
    FragmentMapsBinding::inflate,MapsViewModel::class.java
) {

    private lateinit var mapsAdapter: MapsAdapter

    override fun onViewCreateInvoke() {
        super.onViewCreateInvoke()

        init()
    }

    private fun init(){
        observer()
        setRecycler()
    }

    private fun observer() {
        lifecycleScope.launch{
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.maps.collectLatest {
                    when{
                        it.isLoading -> {
                            binding?.pbProgress?.isVisible = true
                        }
                        it.maps.isNullOrEmpty().not() -> {
                            binding?.pbProgress?.isVisible = false
                            mapsAdapter.mapList = it.maps ?: emptyList()
                        }
                    }
                }
            }
        }
    }

    private fun setRecycler(){
        mapsAdapter = MapsAdapter(::recyclerClickListener)
        binding?.rvMaps?.let { it.apply {
            adapter = mapsAdapter
            layoutManager = LinearLayoutManager(context)
        }
        }
    }

    private fun recyclerClickListener(map: Map){
        val action = MapsFragmentDirections.actionMapsFragmentToMapDetailFragment(map.uuid)
        navController?.navigate(action)
    }
}