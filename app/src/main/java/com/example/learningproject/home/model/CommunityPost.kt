package com.example.learningproject.home.model

data class CommunityPost(
    val id: String,
    val text: String,
    val date: String,
    val publisher: String,
) {
    constructor() : this("", "", "", "")
}