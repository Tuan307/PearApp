package com.example.learningproject.home.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.navigation.fragment.findNavController
import com.example.learningproject.R
import com.example.learningproject.base.BaseFragmentMVVMFactory
import com.example.learningproject.databinding.FragmentDiaryBinding
import com.example.learningproject.home.dialog.DialogResult
import com.example.learningproject.home.viewmodel.DiaryViewModel


@Suppress("DEPRECATION")
class DiaryFragment : BaseFragmentMVVMFactory<FragmentDiaryBinding, DiaryViewModel>() {


    private val diaryAdapter: DiaryAdapter by lazy {
        DiaryAdapter(){

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.model = viewModel
        binding.apply {
            imgBackDiary.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        setUpRecyclerView()
        viewModel.getData()
        viewModel.getList().observe(viewLifecycleOwner) {
            diaryAdapter.setData(it)
        }
        viewModel.getMessage().observe(viewLifecycleOwner) {
            val dialog = DialogResult()
            if (it != null) {
                dialog.showDialog(requireActivity(), it)
                viewModel.setMessage()
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.diaryRecyclerView.setHasFixedSize(true)
        binding.diaryRecyclerView.adapter = diaryAdapter
    }


    override fun onStart() {
        super.onStart()
        val window = requireActivity().window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = requireActivity().resources.getColor(R.color.toolBar)
    }

    override fun onPause() {
        super.onPause()
        Log.e("Check", "Pause")
        val window = requireActivity().window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = requireActivity().resources.getColor(R.color.homeColor)
    }

    override fun onStop() {
        super.onStop()
        Log.e("Check", "Stop")
    }

    override fun getFragmentView(): Int = R.layout.fragment_diary

    override fun getViewModel(): Class<DiaryViewModel> = DiaryViewModel::class.java


}