package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.domain.repository.UserRepository

class SaveUserInfoUseCase(private val userRepository: UserRepository) {

    fun execute(userInfo: UserInfo){
        userRepository.saveData(userInfo)
    }
}