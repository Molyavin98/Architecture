package com.molyavin.mvvm.domain.models

import com.molyavin.mvvm.data.model.UserDTO

data class UserVM(
    val id: String,
    val urlImage: String,
    val email: String,
    val creationTime: String? = null,
    val lastLoginTime: String? = null,
) {
    companion object {
        fun empty(): UserVM = UserVM(id = "", urlImage = "", email = "")
    }
}

fun UserDTO.toVM(creationTime: String, lastLoginTime: String): UserVM {
    return UserVM(
        id = id,
        urlImage = "https://ichef.bbci.co.uk/news/800/cpsprodpb/11097/production/_109238796_gettyimages-450997371.jpg",
        email = email,
        creationTime = creationTime,
        lastLoginTime = lastLoginTime
    )
}