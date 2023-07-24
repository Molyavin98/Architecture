package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.data.repositories.UserRepository

class SaveUserInfoUseCase(private val userRepository: UserRepository) : IUseCase.ISaveUserInfoUseCase {

    override fun execute(income: UserInfo){
        userRepository.saveData(income)
    }
}