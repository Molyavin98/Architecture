package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.domain.models.UserInfo

class CheckFieldUseCase(private val userRepository: UserRepository) : IUseCase<UserInfo,Any?> {
    override fun execute(income: UserInfo): Boolean {
        userRepository.readData()
        return !income.phone.isNullOrEmpty() && !income.passwordOne.isNullOrEmpty() && !income.passwordTwo.isNullOrEmpty()
    }
}