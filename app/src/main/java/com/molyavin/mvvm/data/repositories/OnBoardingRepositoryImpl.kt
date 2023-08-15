package com.molyavin.mvvm.data.repositories

import com.molyavin.mvvm.data.storage.DBSharedPreference

private const val KEY_STATUS = "Status"
private const val DEFAULT = "On"

class OnBoardingRepositoryImpl(private val sharedPreference: DBSharedPreference) :
    OnBoardingRepository {


    override fun saveStatus(status: String) {
        sharedPreference.saveData(KEY_STATUS, status)
    }

    override fun readStatus(): String {
        return sharedPreference.getData(KEY_STATUS) ?: DEFAULT

    }
}