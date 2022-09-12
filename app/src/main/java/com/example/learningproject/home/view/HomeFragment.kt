package com.example.learningproject.home.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.learningproject.R
import com.example.learningproject.base.BaseFragmentMVVM
import com.example.learningproject.databinding.FragmentHomeBinding
import com.example.learningproject.home.viewmodel.HomeViewModel


class HomeFragment : BaseFragmentMVVM<FragmentHomeBinding, HomeViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.model = viewModel
        viewModel.getClickStatus().observe(viewLifecycleOwner, Observer {

        })
    }

    override fun getFragmentView(): Int = R.layout.fragment_home

    override fun getViewModel(): Class<HomeViewModel> = HomeViewModel::class.java


}