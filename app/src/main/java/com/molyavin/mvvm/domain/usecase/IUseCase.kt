package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.domain.models.UserInfo

interface IUseCase<T, R> {
    fun execute(income: T): R
    interface IReadUserInfoUseCase : IUseCase<Any?, UserInfo>
    interface ISaveUserInfoUseCase : IUseCase<UserInfo, Unit>
    interface ICheckFieldUseCase:IUseCase<UserInfo, Any?>
    interface ISetStatusOnBoardingUseCase:IUseCase<String, Unit>
    interface IGetStatusOnBoardingUseCase:IUseCase<Any?, String>
    interface ISetStatusRememberMeUseCase:IUseCase<String, Unit>
    interface IGetStatusRememberMeUseCase:IUseCase<Any?, String>
}