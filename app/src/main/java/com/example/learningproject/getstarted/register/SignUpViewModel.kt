package com.example.learningproject.getstarted.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningproject.getstarted.urils.Constant
import com.example.learningproject.network.BaseNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    val inputName: MutableLiveData<String> = MutableLiveData()
    val inputEmail: MutableLiveData<String> = MutableLiveData()
    val inputPassword: MutableLiveData<String> = MutableLiveData()
    val inputConfirmPassword: MutableLiveData<String> = MutableLiveData()
    private val processBar: MutableLiveData<Boolean> = MutableLiveData()
    fun getProgressBarStatus() = processBar as LiveData<Boolean>
    private val inputMessage: MutableLiveData<String> = MutableLiveData()
    fun getSignUpMessage() = inputMessage as LiveData<String>

    fun signUp() {
        viewModelScope.launch(Dispatchers.IO) {
            processBar.postValue(true)
            val name = inputName.value
            val email = inputEmail.value
            val password = inputPassword.value
            val confirmPassword = inputConfirmPassword.value
            if (name == null || email == null || password == null || confirmPassword == null) {
                inputMessage.postValue(Constant.blankField)
                processBar.postValue(false)
            } else {
                if (password.length >= 6 && confirmPassword == password) {
                    BaseNetwork.fireAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val fireAuth = BaseNetwork.fireAuth.currentUser
                                val userId = fireAuth?.uid ?: email
                                val hm = HashMap<String, Any>()
                                hm["fullName"] = name
                                hm["email"] = email
                                hm["userId"] = userId
                                /*
                                    this newUser is used to check if the user is new or not
                                    if the user is new, navigate to the datacollection activitity
                                    else navigate to the home page of the application
                                hm["newUser"] = false
                                 */
                                BaseNetwork.dataRef.child("Users").child(userId).updateChildren(hm)
                                    .addOnCompleteListener {
                                        if (task.isSuccessful) {

                                            inputMessage.postValue(Constant.isSuccessful)
                                        } else {
                                            inputMessage.postValue(Constant.isError)

                                        }
                                    }
                            } else {
                                inputMessage.postValue(Constant.isError)
                            }
                            processBar.postValue(false)
                        }
                } else {
                    processBar.postValue(false)
                    inputMessage.postValue(Constant.passwordError)
                }
            }
        }
    }

    fun moveToSignIn() {
        inputMessage.value = Constant.moveToSignIn
    }
}