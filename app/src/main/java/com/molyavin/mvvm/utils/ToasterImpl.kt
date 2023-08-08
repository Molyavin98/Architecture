package com.molyavin.mvvm.utils

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

class ToasterImpl @Inject constructor(val context: Context) : Toaster {

    override fun show(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLong(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}