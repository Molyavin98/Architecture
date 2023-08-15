package com.molyavin.mvvm.data.repositories

interface OnBoardingRepository {

    fun saveStatus(status: String)

    fun readStatus(): String

}