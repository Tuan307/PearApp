package com.example.learningproject.home.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button
import android.widget.Toast
import com.example.learningproject.R
import com.example.learningproject.getstarted.login.LoginActivity
import com.example.learningproject.network.BaseNetwork

class DialogConfirmLogout {

    fun showLogOutDialog(activity: Activity) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_confirm_logout)

        val btnLogOut: Button = dialog.findViewById(R.id.btnLogout)
        val btnCancel: Button = dialog.findViewById(R.id.btnCancelLogout)

        btnLogOut.setOnClickListener {
            logOut(activity)
        }
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun logOut(activity: Activity) {
        val user = BaseNetwork.fireAuth.currentUser?.uid ?: ""
        if (user != "") {
            BaseNetwork.fireAuth.signOut()
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        } else {
            Toast.makeText(activity, "Something went wrong, Please try again", Toast.LENGTH_SHORT)
                .show()
        }
    }
}