package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.domain.models.UserInfo

class CheckFieldUseCase(private val userRepository: UserRepository) {

    fun execute(userInfo: UserInfo): Boolean {
        userRepository.readData()
        return !userInfo.fullName.isNullOrEmpty() && !userInfo.phone.isNullOrEmpty() && !userInfo.password.isNullOrEmpty()

    }
}