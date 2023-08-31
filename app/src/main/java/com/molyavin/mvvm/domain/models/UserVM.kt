package com.molyavin.mvvm.domain.models

import com.molyavin.mvvm.data.model.UserDTO
data class UserVM(
    val id: String,
    val email: String,
    val creationTime: String? = null,
    val lastLoginTime: String? = null,
) {
    companion object {
        fun empty(): UserVM = UserVM(id = "", email = "")
    }
}

fun UserDTO.toVM(creationTime: String, lastLoginTime: String): UserVM {
    return UserVM(
        id = id,
        email = email,
        creationTime = creationTime,
        lastLoginTime = lastLoginTime
    )
}