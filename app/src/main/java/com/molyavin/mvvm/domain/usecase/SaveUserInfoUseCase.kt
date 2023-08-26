package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.data.repositories.UserRepository
import javax.inject.Inject

class SaveUserInfoUseCase(private val userRepository: UserRepository) :
    IUseCase<UserInfo, Unit> {

    override fun execute(income: UserInfo) {
        userRepository.saveData(income)
    }
}