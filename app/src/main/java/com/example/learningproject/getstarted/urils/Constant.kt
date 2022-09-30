package com.example.learningproject.getstarted.urils

import java.text.DateFormat
import java.text.SimpleDateFormat

object Constant {
    //login&register
    const val moveToSignUp = "move_to_sign_up"
    const val moveToHomePage = "move_to_home_page"
    const val moveToSignIn = "move_to_sign_in"
    const val loginPasswordError = "password_error"
    const val loginError = "error"
    const val blankField = "blank_field"
    const val isSuccessful = "is_successful"
    const val isError = "is_error"
    const val passwordError = "password_error"

    //post
    const val accessDiary = "access_diary"
    const val talkPrivately = "talk_privately"

    val df: DateFormat = SimpleDateFormat("EEE, d MMM yyyy, HH:mm")

    //diary
    const val diarySuccess = "Successfully syncing data to cloud"
    const val diarySuccessBackWard = "Successfully syncing data from cloud"
    const val diaryError = "Can't sync data to cloud, please check your internet and try again..."
}