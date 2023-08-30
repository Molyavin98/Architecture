package com.molyavin.mvvm.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val id: String,
    val email: String,
    val creationTime: Long,
    val lastLoginTime: Long,
)
