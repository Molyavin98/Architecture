package com.molyavin.mvvm.data.repositories

interface SettingRepository {

    fun saveSetting(key: String, setting: Boolean)
    fun readSetting(key: String): Boolean
}