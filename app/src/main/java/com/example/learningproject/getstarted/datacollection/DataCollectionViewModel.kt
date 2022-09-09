package com.example.learningproject.getstarted.datacollection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningproject.getstarted.urils.Constant
import com.example.learningproject.network.BaseNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataCollectionViewModel : ViewModel() {
    val inputNickName: MutableLiveData<String> = MutableLiveData()
    val inputAge: MutableLiveData<String> = MutableLiveData()
    val inputGender: MutableLiveData<String> = MutableLiveData()
    val inputCountry: MutableLiveData<String> = MutableLiveData()
    private val status: MutableLiveData<String> = MutableLiveData()
    fun getStatus() = status as LiveData<String>


    fun moveToHomePage() {
        status.value = Constant.moveToHomePage
        updateData()
    }

    private fun updateData() {
        val id = BaseNetwork.fireAuth.uid
        val nickname = inputNickName.value ?: ""
        val age = inputAge.value?.toInt() ?: 0
        val gender = inputGender.value ?: ""
        val country = inputCountry.value ?: ""

        if (id != null) {
            viewModelScope.launch(Dispatchers.IO) {
                val hm: HashMap<String, Any> = HashMap()
                hm["nickname"] = nickname
                hm["age"] = age
                hm["gender"] = gender
                hm["country"] = country
                BaseNetwork.dataRef.child("Users").child(id).updateChildren(hm)
            }
        } else {
            status.value = Constant.isError
        }
    }

}