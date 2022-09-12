package com.example.learningproject.home.roomdb

import com.example.learningproject.home.model.Post

class PostRepository(private val dao: PostDAO) {

    suspend fun getAllDiary(): List<Post> {
        return dao.getAllPost()
    }

    suspend fun insertPost(post: Post) {
        dao.insertPost(post)
    }
}