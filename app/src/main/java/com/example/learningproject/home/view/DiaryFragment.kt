package com.example.learningproject.home.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.learningproject.R
import com.example.learningproject.base.BaseFragmentMVVMFactory
import com.example.learningproject.databinding.FragmentDiaryBinding
import com.example.learningproject.home.model.Post
import com.example.learningproject.home.viewmodel.DiaryViewModel


class DiaryFragment : BaseFragmentMVVMFactory<FragmentDiaryBinding, DiaryViewModel>() {


    private lateinit var diaryAdapter: DiaryAdapter
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = DataBindingUtil.inflate(
//            inflater,
//            R.layout.fragment_diary,
//            container,
//            false
//        )
//        binding.lifecycleOwner = this
//        val dao = PostDatabase.getInstance(requireContext()).postDao()
//        val repository = PostRepository(dao)
//        val factory = AppViewModelFactory(repository)
//        viewModel = ViewModelProvider(this, factory)[DiaryViewModel::class.java]
//
//        return binding.root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
    }

    private fun setUpRecyclerView(list: List<Post>) {
        diaryAdapter = DiaryAdapter(list)
        binding.diaryRecyclerView.adapter = diaryAdapter
        binding.diaryRecyclerView.setHasFixedSize(true)
    }

    override fun getFragmentView(): Int = R.layout.fragment_diary

    override fun getViewModel(): Class<DiaryViewModel> = DiaryViewModel::class.java


}