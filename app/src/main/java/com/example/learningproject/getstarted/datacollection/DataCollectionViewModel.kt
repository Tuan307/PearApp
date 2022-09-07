package com.example.learningproject.getstarted.datacollection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataCollectionViewModel : ViewModel() {
    val inputNickName:MutableLiveData<String> = MutableLiveData()
    val inputAge:MutableLiveData<Int> = MutableLiveData()
    val inputGender:MutableLiveData<String> = MutableLiveData()
    val inputCountry:MutableLiveData<String> = MutableLiveData()


}