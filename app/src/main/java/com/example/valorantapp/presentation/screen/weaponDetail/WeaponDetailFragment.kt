package com.example.valorantapp.presentation.screen.weaponDetail

import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.valorantapp.core.base.BaseFragment
import com.example.valorantapp.data.model.weapons.Skin
import com.example.valorantapp.databinding.FragmentWeaponDetailBinding
import com.example.valorantapp.presentation.adapter.WeaponSkinAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeaponDetailFragment : BaseFragment<FragmentWeaponDetailBinding, WeaponDetailViewModel>(
    FragmentWeaponDetailBinding::inflate,WeaponDetailViewModel::class.java
) {

    private val navArgs: WeaponDetailFragmentArgs by navArgs()
    private val weaponSkinAdapter = WeaponSkinAdapter(::clickRecyclerItem)
    private var weaponUuid: String? = null

    override fun onViewCreateInvoke() {
        super.onViewCreateInvoke()

        init()
        observer()
        setRecycler()
    }

    private fun observer(){
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.weaponData.collectLatest {
                    when{
                        it.isLoading -> {
                            binding?.pbProgress?.isVisible = true
                        }
                        it.weaponData != null -> {
                            binding?.pbProgress?.isVisible = false
                            binding?.data = it.weaponData
                            binding?.skin = it.weaponData.skins?.get(0)
                            it.weaponData.skins?.let { skins ->
                                weaponSkinAdapter.skinList = skins
                            }
                        }
                    }
                }
            }
        }
    }

    private fun init(){
        weaponUuid = navArgs.weaponUuid
        weaponUuid?.let {
            viewModel.getWeaponDetail(it)
        }
    }

    private fun clickRecyclerItem(skin: Skin){
        binding?.skin = skin
    }

    private fun setRecycler(){
        binding?.rvSkins?.let {
            it.apply {
                adapter = weaponSkinAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            }
        }
    }

}