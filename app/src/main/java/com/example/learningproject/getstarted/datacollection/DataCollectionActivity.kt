package com.example.learningproject.getstarted.datacollection

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.learningproject.R
import com.example.learningproject.databinding.ActivityDataCollectionBinding
import com.example.learningproject.getstarted.urils.Constant
import com.example.learningproject.home.HomeActivity

class DataCollectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataCollectionBinding
    private lateinit var viewModel: DataCollectionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_collection)
        viewModel = ViewModelProvider(this)[DataCollectionViewModel::class.java]

        binding.model = viewModel
        viewModel.getStatus().observe(this, Observer {
            if (it.equals(Constant.moveToHomePage)) {
                startActivity(Intent(this@DataCollectionActivity, HomeActivity::class.java))
                finish()
            }
        })

    }
}