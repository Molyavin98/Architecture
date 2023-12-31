package com.molyavin.mvvm.domain.usecase.auth

import com.molyavin.mvvm.domain.models.NewUserVM
import com.molyavin.mvvm.domain.usecase.base.IUseCase
import javax.inject.Inject

class ValidateRegisterUserInfoUseCase @Inject constructor() : IUseCase<NewUserVM, Any?> {

    override fun execute(income: NewUserVM): Boolean{
        return !income.email.isNullOrEmpty() || !income.password.isNullOrEmpty() || !income.passwordConfirm.isNullOrEmpty()
    }
}