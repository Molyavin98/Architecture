package com.molyavin.mvvm.data.repositories

import com.molyavin.mvvm.data.storage.DBSharedPreference
import com.molyavin.mvvm.domain.models.UserInfo

private const val KEY_FULL_NAME = "FullName"
private const val KEY_PHONE = "Phone"
private const val KEY_PASSWORD = "Password"
private const val DEFAULT = "null"

class UserRepositoryImpl(private val sharedPreferences: DBSharedPreference) : UserRepository {

    override fun saveData(userInfo: UserInfo) {
        sharedPreferences.saveData(KEY_FULL_NAME, userInfo.fullName)
        sharedPreferences.saveData(KEY_PHONE, userInfo.phone)
        sharedPreferences.saveData(KEY_PASSWORD, userInfo.password)
    }

    override fun readData(): UserInfo = UserInfo(
        fullName = sharedPreferences.getData(KEY_FULL_NAME) ?: DEFAULT,
        phone = sharedPreferences.getData(KEY_PHONE) ?: DEFAULT,
        password = sharedPreferences.getData(KEY_PASSWORD) ?: DEFAULT,
    )

}