package com.example.learningproject.getstarted.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningproject.getstarted.urils.Constant
import com.example.learningproject.network.BaseNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val message: MutableLiveData<String> = MutableLiveData()
    fun getMessage() = message as LiveData<String>
    val emailInput: MutableLiveData<String> = MutableLiveData()
    val passwordInput: MutableLiveData<String> = MutableLiveData()

    fun moveToSignUp() {
        message.value = Constant.moveToSignUp
    }

    fun signIn() {
        viewModelScope.launch(Dispatchers.IO) {
            val email = emailInput.value.toString()
            val password = passwordInput.value.toString()
            Log.e("checkInput", password)
            val auth = BaseNetwork.fireAuth
            if (email != "" && password != "" && email != null && password != null) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        message.postValue(Constant.moveToHomePage)
                    } else {
                        message.postValue(Constant.loginPasswordError)
                    }
                }
            } else {
                message.postValue(Constant.loginError)
            }
        }
    }
}