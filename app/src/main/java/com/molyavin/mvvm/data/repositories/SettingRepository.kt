package com.molyavin.mvvm.data.repositories

interface SettingRepository {

    fun saveSetting(key: String, setting: String)
    fun readSetting(key: String): String
}