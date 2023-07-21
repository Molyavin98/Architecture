package com.molyavin.mvvm.model

import android.content.Context
import android.content.SharedPreferences
import com.molyavin.mvvm.MainContract

class DBSharedPreference(context: Context) : MainContract.Model {

    private var sharedPreferences: SharedPreferences
    private var editor: SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences("DataBase", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    override fun saveData(key: String, data: String) {
        editor.putString(key, data)
        editor.apply()
    }

    override fun getData(key: String): String? {
        return sharedPreferences.getString(key, null)
    }
}
