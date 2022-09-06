package com.example.learningproject.getstarted.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.learningproject.R
import com.example.learningproject.databinding.ActivityLoginBinding
import com.example.learningproject.getstarted.register.SignUpActivity
import com.example.learningproject.getstarted.urils.Constant

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.model = viewModel

        viewModel.getMessage().observe(this, Observer {
            when (it) {
                Constant.moveToHomePage -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Move to Home",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Constant.moveToSignUp -> {
                    startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
                    //finish()
                }
                Constant.loginPasswordError -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Your password is Incorrect",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Constant.loginError -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please type in all fields",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}