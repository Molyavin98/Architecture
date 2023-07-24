package com.molyavin.mvvm.data.repositories

import com.molyavin.mvvm.domain.models.UserInfo

interface UserRepository {

    fun saveData(userInfo: UserInfo)
    fun  readData(): UserInfo

}