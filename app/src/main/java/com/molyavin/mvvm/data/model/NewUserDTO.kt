package com.molyavin.mvvm.data.model

import com.molyavin.mvvm.domain.models.NewUserVM

data class NewUserDTO(val email: String, val password: String, val passwordConfirm: String?)

fun NewUserVM.toDTO(): NewUserDTO {
    return NewUserDTO(email = email, password = password, passwordConfirm = passwordConfirm)
}