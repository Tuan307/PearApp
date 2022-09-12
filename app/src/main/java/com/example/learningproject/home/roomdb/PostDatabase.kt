package com.example.learningproject.home.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.learningproject.home.model.Post

@Database(entities = [Post::class], version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDAO

    companion object {
        @Volatile
        private var INSTANCE: PostDatabase? = null
        fun getInstance(context: Context): PostDatabase {
            var instace = INSTANCE
            if (instace == null) {
                instace = Room.databaseBuilder(
                    context.applicationContext,
                    PostDatabase::class.java,
                    "user_post_db"
                ).build()
            }
            return instace
        }
    }

}