package com.molyavin.mvvm.domain.models

import com.molyavin.mvvm.data.model.UserDTO
data class UserVM(
    val id: String,
    val email: String,
    val creationTime: Long,
    val lastLoginTime: Long,
) {
    companion object {
        fun empty(): UserVM = UserVM(id = "", email = "", creationTime = 0, lastLoginTime = 0)
    }
}
fun UserVM.toDTO(): UserDTO {
    return UserDTO(
        id = id,
        email = email,
        creationTime = creationTime,
        lastLoginTime = lastLoginTime
    )
}
fun UserDTO.toVM(): UserVM {
    return UserVM(
        id = id,
        email = email,
        creationTime = creationTime,
        lastLoginTime = lastLoginTime
    )
}