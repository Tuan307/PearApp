package com.example.learningproject.home.dialog

import android.app.Activity
import android.app.Dialog
import android.view.Window
import android.widget.TextView
import com.example.learningproject.R

class DialogResult {

    fun showDialog(activity: Activity, message: String) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_result)

        val txt: TextView = dialog.findViewById(R.id.txtDialogMessage)
        txt.text = message
        dialog.show()
    }
}