package com.example.learningproject.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object BaseNetwork {

    val dataRef =
        FirebaseDatabase.getInstance("https://pearup-9bf63-default-rtdb.asia-southeast1.firebasedatabase.app")
            .reference

    val fireAuth = FirebaseAuth.getInstance()
}