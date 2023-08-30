package com.molyavin.mvvm.data.storage

import android.content.Context
import android.content.SharedPreferences

class DBSharedPreference(context: Context) {

    private var sharedPreferences: SharedPreferences
    private var editor: SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences("DataBase", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun saveData(key: String,data: String){
        editor.putString(key,data)
        editor.apply()
    }
    fun getData(key: String) :String? {
        return sharedPreferences.getString(key, null)
    }

    fun saveValue(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }
    fun getValue(key: String): Boolean {
        return sharedPreferences.getBoolean(key,false)
    }
}