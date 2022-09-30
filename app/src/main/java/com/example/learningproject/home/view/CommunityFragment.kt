package com.example.learningproject.home.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.learningproject.R
import com.example.learningproject.base.BaseFragmentMVVM
import com.example.learningproject.databinding.FragmentCommunityBinding
import com.example.learningproject.home.model.CommunityPost
import com.example.learningproject.home.viewmodel.CommunityViewModel


class CommunityFragment : BaseFragmentMVVM<FragmentCommunityBinding, CommunityViewModel>() {
    private lateinit var communityAdapter: CommunityAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.model = viewModel
        setUpRecyclerView()
        viewModel.getList().observe(viewLifecycleOwner) {
            communityAdapter.setData(it)
        }

        binding.apply {
            edtSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Toast.makeText(activity, query.toString(), Toast.LENGTH_SHORT).show()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.e("CheckData", newText.toString())
                    return true
                }
            })
        }

    }

    private fun setUpRecyclerView() {
        communityAdapter = CommunityAdapter() { post: CommunityPost ->
            onItemClick(post)
        }
        binding.communityRecyclerView.adapter = communityAdapter
    }

    override fun getFragmentView(): Int = R.layout.fragment_community

    override fun getViewModel(): Class<CommunityViewModel> = CommunityViewModel::class.java
    private fun onItemClick(post: CommunityPost) {
        Toast.makeText(activity, post.text, Toast.LENGTH_SHORT).show()
    }


}