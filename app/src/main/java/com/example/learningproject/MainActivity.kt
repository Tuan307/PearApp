package com.example.learningproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.learningproject.databinding.ActivityMainBinding
import com.example.learningproject.getstarted.login.LoginActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.apply {
//            imgWelcome.setOnClickListener {
//                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
//                finish()
//            }
//        }
        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }, 1500)
    }
}