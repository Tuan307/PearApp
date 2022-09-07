package com.example.learningproject.getstarted.datacollection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.learningproject.R
import com.example.learningproject.databinding.ActivityDataCollectionBinding

class DataCollectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataCollectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_data_collection)

    }
}