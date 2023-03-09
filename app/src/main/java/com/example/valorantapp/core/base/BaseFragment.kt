package com.example.valorantapp.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.valorantapp.R


abstract class BaseFragment<DB: ViewDataBinding, VM: ViewModel>(
    private val inflateLayout : (LayoutInflater, ViewGroup?, Boolean) -> DB,
    private val viewModelClass: Class<VM>
) : Fragment() {
    private var _binding : DB? = null
    val binding get() = _binding

    protected val viewModel: VM by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    protected val navController by lazy {
        activity?.let {
            Navigation.findNavController(it , R.id.navHostFragment)
        }
    }

    open fun onViewCreateInvoke(){}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateLayout(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreateInvoke()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}