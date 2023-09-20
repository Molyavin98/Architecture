package com.molyavin.mvvm.data.model

data class UserDTO(
    val id: String,
    val email: String,
    val urlImage:String,
    val creationTime: Long,
    val lastLoginTime: Long,
)
