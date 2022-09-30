package com.example.learningproject.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningproject.getstarted.urils.Constant
import com.example.learningproject.home.model.Post
import com.example.learningproject.home.roomdb.PostRepository
import com.example.learningproject.network.BaseNetwork
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiaryViewModel(private val repository: PostRepository) : ViewModel() {
    private val listOfPost: MutableLiveData<List<Post>> = MutableLiveData()
    fun getList() = listOfPost as LiveData<List<Post>>
    private val message: MutableLiveData<String?> = MutableLiveData()
    fun getMessage() = message as LiveData<String?>
    fun setMessage() {
        message.postValue(null)
    }

    fun getData() {
        viewModelScope.launch {
            val list = repository.getAllDiary()
            listOfPost.postValue(list)
        }
    }

    fun insertData(post: Post) {
        viewModelScope.launch {
            repository.insertPost(post)
        }
    }

    fun syncData() {
        val uid = BaseNetwork.fireAuth.currentUser?.uid ?: ""
        if (listOfPost.value!!.isNotEmpty()) {
            Log.e("CheckData", "Yes")
            viewModelScope.launch(Dispatchers.IO) {
                val ref = BaseNetwork.dataRef.child("Diary").child(uid)
                val list = listOfPost.value ?: listOf(null)
                ref.setValue(list).addOnCompleteListener { p0 ->
                    if (p0.isSuccessful) {
                        message.postValue(Constant.diarySuccess)
                    } else {
                        message.postValue(Constant.diaryError)
                    }
                }
            }
        } else {
            Log.e("CheckData", "No")
            viewModelScope.launch(Dispatchers.IO) {
                val list: ArrayList<Post> = ArrayList()
                BaseNetwork.dataRef.child("Diary").child(uid)
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            for (data in snapshot.children) {
                                val post = data.getValue(Post::class.java)
                                if (post != null) {
                                    list.add(post)
                                    insertData(post)
                                }
                            }
                            message.postValue(Constant.diarySuccessBackWard)
                            getData()
                        }

                        override fun onCancelled(error: DatabaseError) {
                            message.postValue(Constant.diaryError)
                        }
                    })
            }
        }
    }

}