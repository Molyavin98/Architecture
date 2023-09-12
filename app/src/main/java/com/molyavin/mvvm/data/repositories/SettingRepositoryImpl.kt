package com.molyavin.mvvm.data.repositories

import com.molyavin.mvvm.data.storage.DBSharedPreference

class SettingRepositoryImpl(private val sharedPreference: DBSharedPreference) : SettingRepository {

    override fun saveSetting(key: String, setting: Boolean) {
        sharedPreference.saveValue(key = key, value = setting)
    }

    override fun readSetting(key: String, defValue: Boolean): Boolean {
        return sharedPreference.getValue(key = key, defValue = defValue)
    }

}