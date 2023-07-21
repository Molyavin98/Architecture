package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.domain.repository.UserRepository

class ReadUserInfoUseCase(private val userRepository: UserRepository) {

    fun execute() : UserInfo{
        return userRepository.readData()
    }
}