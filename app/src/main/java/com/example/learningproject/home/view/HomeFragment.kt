package com.example.learningproject.home.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.learningproject.R
import com.example.learningproject.base.BaseFragmentMVVM
import com.example.learningproject.databinding.FragmentHomeBinding
import com.example.learningproject.getstarted.urils.Constant
import com.example.learningproject.home.viewmodel.HomeViewModel


class HomeFragment : BaseFragmentMVVM<FragmentHomeBinding, HomeViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.model = viewModel
        viewModel.getClickStatus().observe(viewLifecycleOwner, Observer {
            when (it) {
                Constant.accessDiary -> {
                    findNavController().navigate(
                        R.id.communityFragment,
                        null, NavOptions.Builder().setPopUpTo(R.id.homeFragment, true).build()
                    )
                }
            }
        })
        binding.apply {
            btnAddDiary.setOnClickListener {
                findNavController().navigate(
                    R.id.postFragment, null, NavOptions.Builder()
                        .setPopUpTo(R.id.homeFragment, true)
                        .build()
                )
            }
        }
    }

    override fun getFragmentView(): Int = R.layout.fragment_home

    override fun getViewModel(): Class<HomeViewModel> = HomeViewModel::class.java


}