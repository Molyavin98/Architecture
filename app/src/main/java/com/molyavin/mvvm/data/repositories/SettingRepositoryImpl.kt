package com.molyavin.mvvm.data.repositories

import com.molyavin.mvvm.data.storage.DBSharedPreference

class SettingRepositoryImpl (private val sharedPreference: DBSharedPreference) : SettingRepository {

    override fun saveSetting(key: String, setting: String) {
       sharedPreference.saveData(key,setting)
    }

    override fun readSetting(key: String): String {
      return  sharedPreference.getData(key) ?: "Off"
    }


}