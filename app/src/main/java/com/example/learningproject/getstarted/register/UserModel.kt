package com.example.learningproject.getstarted.register

data class UserModel(
    private val fullName: String,
    private val email: String,
    private val passWord: String,
    private val nickName: String
) {
    constructor() : this("", "", "", "")

    fun checkValidate(): Boolean {
        if (passWord.length >= 6) {
            return true
        }
        return false
    }
}