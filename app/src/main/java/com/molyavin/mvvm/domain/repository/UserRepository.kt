package com.molyavin.mvvm.domain.repository

import com.molyavin.mvvm.domain.models.UserInfo

interface UserRepository {

    fun saveData(userInfo: UserInfo)
    fun  readData(): UserInfo

}