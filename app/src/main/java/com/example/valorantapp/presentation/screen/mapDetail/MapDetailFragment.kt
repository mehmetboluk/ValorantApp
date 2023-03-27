package com.example.valorantapp.presentation.screen.mapDetail

import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.valorantapp.core.base.BaseFragment
import com.example.valorantapp.databinding.FragmentMapDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapDetailFragment : BaseFragment<FragmentMapDetailBinding, MapDetailViewModel>(
    FragmentMapDetailBinding::inflate, MapDetailViewModel::class.java
) {

    private val navArgs : MapDetailFragmentArgs by navArgs()

    override fun onViewCreateInvoke() {
        super.onViewCreateInvoke()

        init()
        observer()
    }

    private fun observer() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getMapDetail.collectLatest {
                    when{
                        it.isLoading -> {
                            binding?.pbProgress?.isVisible = true
                        }
                        it.map != null -> {
                            binding?.pbProgress?.isVisible = false
                            binding?.data = it.map
                        }
                    }
                }
            }
        }
    }

    private fun init(){
        navArgs.mapUUID?.let { uuid ->
            viewModel.getMapData(uuid)
        }
    }
}