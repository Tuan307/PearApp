package com.example.learningproject.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningproject.home.model.Post
import com.example.learningproject.home.roomdb.PostRepository
import kotlinx.coroutines.launch

class DiaryViewModel(private val repository: PostRepository) : ViewModel() {
    private val listOfPost: MutableLiveData<List<Post>> = MutableLiveData()
    fun getList() = listOfPost as LiveData<List<Post>>

    fun getData() {
        viewModelScope.launch {
            val list = repository.getAllDiary()
            listOfPost.postValue(list)
        }
    }

}