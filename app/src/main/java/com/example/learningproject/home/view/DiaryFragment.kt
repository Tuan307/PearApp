package com.example.learningproject.home.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.learningproject.R
import com.example.learningproject.databinding.FragmentDiaryBinding
import com.example.learningproject.home.model.Post
import com.example.learningproject.home.roomdb.PostDatabase
import com.example.learningproject.home.roomdb.PostRepository
import com.example.learningproject.home.viewmodel.AppViewModelFactory
import com.example.learningproject.home.viewmodel.DiaryViewModel


class DiaryFragment : Fragment() {

    private lateinit var binding: FragmentDiaryBinding
    private lateinit var viewModel: DiaryViewModel
    private lateinit var diaryAdapter: DiaryAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_diary,
            container,
            false
        )
        binding.lifecycleOwner = this
        val dao = PostDatabase.getInstance(requireContext()).postDao()
        val repository = PostRepository(dao)
        val factory = AppViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[DiaryViewModel::class.java]
        binding.model = viewModel
        binding.apply {
            imgBackDiary.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        viewModel.getData()
        viewModel.getList().observe(viewLifecycleOwner, Observer {
            setUpRecyclerView(it)
            Log.e("CheckData", it.size.toString())
        })
        return binding.root
    }

    private fun setUpRecyclerView(list: List<Post>) {
        diaryAdapter = DiaryAdapter(list)
        binding.diaryRecyclerView.adapter = diaryAdapter
        binding.diaryRecyclerView.setHasFixedSize(true)
    }


}