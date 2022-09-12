package com.example.learningproject.home.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_db")
data class Post(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String,
    val date: String
) {
}