package com.example.learningproject.getstarted.started

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.learningproject.R
import com.example.learningproject.databinding.ActivityGetStartedBinding
import com.example.learningproject.getstarted.datacollection.DataCollectionActivity
import com.example.learningproject.getstarted.started.adapter.GetStartedAdapter

class GetStartedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGetStartedBinding
    private lateinit var getStartedAdapter: GetStartedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_get_started)
        val list: List<Int> = listOf(
            R.drawable.onboarding1,
            R.drawable.onboarding2,
            R.drawable.onboarding
        )
        getStartedAdapter = GetStartedAdapter(list)
        binding.apply {
            viewPager2.adapter = getStartedAdapter
            btnGetStarted.setOnClickListener {
                startActivity(Intent(this@GetStartedActivity, DataCollectionActivity::class.java))
                finish()
            }
        }
    }
}