package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.domain.models.UserInfo

class CheckFieldUseCase(private val userRepository: UserRepository) : IUseCase.ICheckFieldUseCase {
    override fun execute(income: UserInfo): Boolean {
        userRepository.readData()
        return !income.fullName.isNullOrEmpty() && !income.phone.isNullOrEmpty() && !income.password.isNullOrEmpty()
    }
}