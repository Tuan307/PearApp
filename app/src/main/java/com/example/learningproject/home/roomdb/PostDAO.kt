package com.example.learningproject.home.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.learningproject.home.model.Post

@Dao
interface PostDAO {

    @Insert
    suspend fun insertPost(post: Post)

    @Query("SELECT * FROM post_db")
    suspend fun getAllPost(): List<Post>

    @Delete
    suspend fun deletePost(post: Post)

}