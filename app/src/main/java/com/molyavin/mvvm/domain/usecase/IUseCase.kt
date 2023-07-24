package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.domain.models.UserInfo

interface IUseCase {

    interface IReadUserInfoUseCase {
        fun execute(): UserInfo
    }

    interface ISaveUserInfoUseCase {
        fun execute(userInfo: UserInfo)
    }
}