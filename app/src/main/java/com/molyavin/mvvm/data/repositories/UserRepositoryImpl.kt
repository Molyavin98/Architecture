package com.molyavin.mvvm.data.repositories

import android.content.Context
import android.content.SharedPreferences
import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.domain.repository.UserRepository

private const val SHARED_PREFS_NAME = "DataBase"
private const val KEY_FULL_NAME = "FullName"
private const val KEY_PHONE = "Phone"
private const val KEY_PASSWORD = "Password"
private const val DEFAULT = "null"

class UserRepositoryImpl(context: Context) : UserRepository {

    private var sharedPreferences: SharedPreferences
    private var editor: SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    override fun saveData(userInfo: UserInfo) {
        editor.putString(KEY_FULL_NAME, userInfo.fullName)
        editor.putString(KEY_PHONE, userInfo.phone)
        editor.putString(KEY_PASSWORD, userInfo.password)
        editor.apply()
    }

    override fun readData(): UserInfo {
        val fullName: String = sharedPreferences.getString(KEY_FULL_NAME, DEFAULT) ?: DEFAULT
        val phone: String = sharedPreferences.getString(KEY_PHONE, DEFAULT) ?: DEFAULT
        val password: String = sharedPreferences.getString(KEY_PASSWORD, DEFAULT) ?: DEFAULT

        return UserInfo(fullName,phone, password)
    }

}