package com.example.valorantapp.presentation.screen.weapons

import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.valorantapp.core.base.BaseFragment
import com.example.valorantapp.databinding.FragmentWeaponsBinding
import com.example.valorantapp.domain.model.Weapon
import com.example.valorantapp.presentation.adapter.WeaponsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeaponsFragment : BaseFragment<FragmentWeaponsBinding, WeaponViewModel>(
    FragmentWeaponsBinding::inflate,WeaponViewModel::class.java
) {

    private lateinit var weaponsAdapter: WeaponsAdapter

    override fun onViewCreateInvoke() {
        super.onViewCreateInvoke()

        setRecycler()
        observer()

    }

    private fun observer() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.weapons.collectLatest {
                    when{
                        it.isLoading -> {
                            binding?.pbProgress?.isVisible = true
                        }
                        it.weaponList.isNullOrEmpty().not() -> {
                            binding?.pbProgress?.isVisible = false
                            weaponsAdapter.weaponList = it.weaponList?.toList() ?: emptyList()
                        }
                    }
                }
            }
        }
    }

    private fun setRecycler(){
        weaponsAdapter = WeaponsAdapter(::weaponRecyclerClick)
        binding?.rvWeapons?.let {
            it.apply {
                adapter = weaponsAdapter
                layoutManager = GridLayoutManager(context,2)
            }
        }
    }

    private fun weaponRecyclerClick(weapon: Weapon?){
        val action = WeaponsFragmentDirections.actionWeaponsFragmentToWeaponDetailFragment(weapon?.uuid ?: "")
        navController?.navigate(action)
    }
}