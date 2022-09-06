package com.example.learningproject.getstarted.register

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.learningproject.R
import com.example.learningproject.databinding.ActivitySignUpBinding
import com.example.learningproject.getstarted.login.LoginActivity
import com.example.learningproject.getstarted.started.GetStartedActivity
import com.example.learningproject.getstarted.urils.Constant

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: SignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        binding.data = viewModel
        viewModel.getSignUpMessage().observe(this, Observer {
            when (it) {
                Constant.passwordError -> {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Something is wrong with your password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Constant.blankField -> {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Please type in all blank field",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Constant.isSuccessful -> {
                    startActivity(Intent(this@SignUpActivity, GetStartedActivity::class.java))
                    finish()
                }
                Constant.isError -> {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Something is wrong,please try again later",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Constant.moveToSignIn -> {
                    startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                    finish()
                }
            }
        })
        viewModel.getProgressBarStatus().observe(this, Observer {
            if (it) {
                binding.signUpProgressBar.visibility = View.VISIBLE
            } else {
                Handler(Looper.myLooper()!!).postDelayed(Runnable {
                    binding.signUpProgressBar.visibility = View.GONE
                }, 1500)
            }
            Log.d("checkInt", it.toString())
        })
    }

}