package com.swengineering.everyportal.utils.classes

import android.app.AlertDialog
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View

class Dialog(private val context: Context, id: Int) {

    private val view: View? =
        LayoutInflater.from(context)
            .inflate(id, null)
    private val builder = AlertDialog.Builder(context)
    private val dialog: AlertDialog = builder
        .setCancelable(false)
        .setView(view)
        .create()

    fun showDialog(second: Long) {
        //로딩
        dialog.show()
        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismiss()
        }, second)
    }

    fun dismissDialog() {
        dialog.dismiss()
    }
}