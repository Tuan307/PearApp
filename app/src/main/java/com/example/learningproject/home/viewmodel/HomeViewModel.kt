package com.example.learningproject.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningproject.getstarted.urils.Constant
import com.example.learningproject.home.model.User
import com.example.learningproject.network.BaseNetwork
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val inputName: MutableLiveData<String> = MutableLiveData()
    private val onClick: MutableLiveData<String> = MutableLiveData()
    fun getClickStatus() = onClick as LiveData<String>
    fun setClickStatus(s: String) {
        inputName.value = s
    }

    init {
        getDataFromDataBase()
    }

    private fun getDataFromDataBase() {
        val uid = BaseNetwork.fireAuth.currentUser?.uid ?: ""
        viewModelScope.launch(Dispatchers.IO) {
            BaseNetwork.dataRef.child("Users").child(uid)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val user = snapshot.getValue(User::class.java)
                        if (user != null) {
                            inputName.postValue("Welcome back,\n ${user.nickname}")
                        } else {
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
        }
    }

    fun onAccessDiary() {
        onClick.value = Constant.accessDiary
    }

    fun onTalkPrivately() {
        onClick.value = Constant.talkPrivately
    }

}