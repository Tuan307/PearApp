package com.example.learningproject.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.learningproject.R
import com.example.learningproject.databinding.FragmentPostBinding
import com.example.learningproject.home.roomdb.PostDatabase
import com.example.learningproject.home.roomdb.PostRepository
import com.example.learningproject.home.viewmodel.AppViewModelFactory
import com.example.learningproject.home.viewmodel.PostViewModel

class PostFragment : Fragment() {
    private lateinit var binding: FragmentPostBinding
    private lateinit var viewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_post,
            container,
            false
        )
        binding.lifecycleOwner = this
        val dao = PostDatabase.getInstance(requireContext()).postDao()
        val repository = PostRepository(dao)
        val factory = AppViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[PostViewModel::class.java]
        binding.model = viewModel
        activity?.window?.statusBarColor = resources.getColor(R.color.postBackGround)
        binding.apply {
            btnPublic.tag = "open"
            btnPrivate.tag = "close"
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
        return binding.root

    }

}