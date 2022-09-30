package com.example.learningproject.home.model

data class User(
    val fullName: String,
    val nickname: String,
    val userId: String,
    val email: String,
    val age: Int,
    val country: String,
    val gender: String
) {
    constructor() : this(
        fullName = "",
        nickname = "",
        userId = "", email = "",
        age = 0,
        country = "",
        gender = ""
    )
}