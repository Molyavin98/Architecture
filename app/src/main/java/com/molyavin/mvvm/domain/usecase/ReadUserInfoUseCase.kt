package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.data.repositories.UserRepository

class ReadUserInfoUseCase(private val userRepository: UserRepository) :
    IUseCase.IReadUserInfoUseCase {
    override fun execute(income: Any?): UserInfo {
        return userRepository.readData()
    }
}