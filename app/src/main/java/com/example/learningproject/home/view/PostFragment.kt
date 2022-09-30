package com.example.learningproject.home.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.learningproject.R
import com.example.learningproject.base.BaseFragmentMVVMFactory
import com.example.learningproject.databinding.FragmentPostBinding
import com.example.learningproject.home.viewmodel.PostViewModel

class PostFragment : BaseFragmentMVVMFactory<FragmentPostBinding, PostViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.model = viewModel
        activity?.window?.statusBarColor = resources.getColor(R.color.postBackGround)
        binding.apply {
            btnPublic.tag = "open"
            btnPrivate.tag = "close"
            viewModel.setInputMode("public")
            btnPublic.setOnClickListener {
                if (btnPublic.tag == "close") {
                    viewModel.setInputMode("public")
                    btnPublic.tag = "open"
                    btnPrivate.tag = "close"
                    btnPublic.setBackgroundResource(R.drawable.dialog_confirm_logout_bg)
                    btnPublic.setTextColor(resources.getColor(R.color.black))
                    btnPrivate.setTextColor(resources.getColor(R.color.white))
                    btnPrivate.setBackgroundColor(resources.getColor(R.color.transColor))
                }
            }
            btnPrivate.setOnClickListener {
                if (btnPrivate.tag == "close") {
                    viewModel.setInputMode("private")
                    btnPublic.tag = "close"
                    btnPrivate.tag = "open"
                    btnPrivate.setBackgroundResource(R.drawable.dialog_confirm_logout_bg)
                    btnPrivate.setTextColor(resources.getColor(R.color.black))
                    btnPublic.setTextColor(resources.getColor(R.color.white))
                    btnPublic.setBackgroundColor(resources.getColor(R.color.transColor))
                }
            }
        }
        viewModel.getErrorMessage().observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            when (it) {
                "Please type something" -> {
                    // do nothing
                }
                else -> {
                    //findNavController().popBackStack(R.id.homeFragment, false)
                }
            }
        })
    }

    override fun getFragmentView(): Int = R.layout.fragment_post

    override fun getViewModel(): Class<PostViewModel> = PostViewModel::class.java

}