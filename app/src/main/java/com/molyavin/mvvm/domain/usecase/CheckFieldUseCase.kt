package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.domain.models.UserInfo

class CheckFieldUseCase(private val userRepository: UserRepository) {

    fun execute(userInfo: UserInfo): Boolean {
        return userRepository.checkData(userInfo)

    }
}