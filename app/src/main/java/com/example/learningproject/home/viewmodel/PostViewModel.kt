package com.example.learningproject.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningproject.home.model.Post
import com.example.learningproject.home.roomdb.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class PostViewModel(private val post: PostRepository) : ViewModel() {

    val inputData: MutableLiveData<String> = MutableLiveData()
    val inputDiary: MutableLiveData<String> = MutableLiveData()
    private val errorMessage: MutableLiveData<String?> = MutableLiveData()
    private val inputMode: MutableLiveData<String> = MutableLiveData()
    fun setInputMode(s: String) {
        inputMode.value = s
    }

    fun getErrorMessage() = errorMessage as LiveData<String?>
    fun setErrorMessage() {
        errorMessage.value = null
    }

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            val df: DateFormat = SimpleDateFormat("EEE, d MMM yyyy, HH:mm")
            val date: String = df.format(Calendar.getInstance().time)
            inputData.postValue(date)
        }
    }

    fun onPostDiary() {
        //do nothing for now
        val mode = inputMode.value ?: ""
        val text = inputDiary.value ?: ""
        val date = inputData.value ?: ""
        if (mode != "") {
            if (mode == "private") {
                Log.e("CheckData", "private")
                if (text != "" && date != "") {
                    viewModelScope.launch(Dispatchers.IO) {
                        post.insertPost(Post(0, text, date))
                        inputDiary.postValue("")
                        errorMessage.postValue("Successfully added your post to diary")

                    }
                } else {
                    errorMessage.value = "Please type something"
                }
            } else {
                // do something later for public community
            }
        }

    }

    fun onClickRestart() {
        inputDiary.value = ""
    }
}