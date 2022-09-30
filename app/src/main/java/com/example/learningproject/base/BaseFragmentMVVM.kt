package com.example.learningproject.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragmentMVVM<T : ViewDataBinding, VM : ViewModel> : Fragment() {
    protected lateinit var binding: T
    protected lateinit var viewModel: VM
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            getFragmentView(),
            container,
            false
        )
        //30/09/2022
        viewModel = ViewModelProvider(this)[getViewModel()]
        binding.lifecycleOwner = this
        return binding.root
    }

    abstract fun getFragmentView(): Int
    abstract fun getViewModel(): Class<VM>
}