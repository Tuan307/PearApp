package com.example.learningproject.home.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.learningproject.R
import com.example.learningproject.base.BaseFragmentMVVM
import com.example.learningproject.databinding.FragmentUserBinding
import com.example.learningproject.home.dialog.DialogConfirmLogout
import com.example.learningproject.home.viewmodel.UserViewModel


class UserFragment : BaseFragmentMVVM<FragmentUserBinding, UserViewModel>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.model = viewModel
        binding.apply {
            imgUserBackArrow.setOnClickListener {
                findNavController().popBackStack()
            }
            txtLogOut.setOnClickListener {
                val dialog = DialogConfirmLogout()
                dialog.showLogOutDialog(requireActivity())
            }
        }
    }

    override fun getFragmentView(): Int = R.layout.fragment_user

    override fun getViewModel(): Class<UserViewModel> = UserViewModel::class.java

}