package com.example.learningproject.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningproject.home.model.User
import com.example.learningproject.network.BaseNetwork
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    val inputName: MutableLiveData<String> = MutableLiveData()
    val inputAge: MutableLiveData<String> = MutableLiveData()
    val inputEmail: MutableLiveData<String> = MutableLiveData()

    init {
        getData()
    }

    private fun getData() {
        val uid = BaseNetwork.fireAuth.currentUser?.uid ?: ""
        viewModelScope.launch(Dispatchers.IO) {
            BaseNetwork.dataRef.child("Users").child(uid)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val user = snapshot.getValue(User::class.java)
                        if (user != null) {
                            Log.e("CheckUser", "not null ${user.nickname}")
                            inputAge.postValue(user.age.toString())
                            inputEmail.postValue(user.email)
                            inputName.postValue(user.nickname)
                        } else {
                            Log.e("CheckUser", "null")
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
        }
    }
}