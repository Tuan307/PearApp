package com.example.learningproject.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningproject.home.model.CommunityPost
import com.example.learningproject.network.BaseNetwork
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommunityViewModel : ViewModel() {
    private val listOfDiary: MutableLiveData<List<CommunityPost>> = MutableLiveData()
    private val list = ArrayList<CommunityPost>()

    fun getList() = listOfDiary as LiveData<List<CommunityPost>>

    init {
        callData();
    }

    private fun callData() {
        viewModelScope.launch(Dispatchers.IO) {
            BaseNetwork.dataRef.child("community")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (data in snapshot.children) {
                            val post = data.getValue(CommunityPost::class.java)
                            if (post != null) {
                                list.add(post)
                            }
                        }
                        listOfDiary.postValue(list)
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
        }
    }
}