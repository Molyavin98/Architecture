package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.data.repositories.UserRepository
import javax.inject.Inject

class ReadUserInfoUseCase @Inject constructor(private val userRepository: UserRepository) :
    IUseCase<Any?, UserInfo> {
    override fun execute(income: Any?): UserInfo {
        return userRepository.readData()
    }
}