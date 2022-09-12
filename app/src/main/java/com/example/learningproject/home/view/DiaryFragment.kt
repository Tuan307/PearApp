package com.example.learningproject.home.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.learningproject.R
import com.example.learningproject.base.BaseFragmentMVVM
import com.example.learningproject.databinding.FragmentDiaryBinding
import com.example.learningproject.home.viewmodel.DiaryViewModel


class DiaryFragment : BaseFragmentMVVM<FragmentDiaryBinding, DiaryViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.model = viewModel
        binding.apply {
            imgBackDiary.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun getFragmentView(): Int = R.layout.fragment_diary

    override fun getViewModel(): Class<DiaryViewModel> = DiaryViewModel::class.java


}