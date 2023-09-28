package com.molyavin.mvvm.domain.usecase.auth

import com.google.firebase.auth.FirebaseUser
import com.molyavin.mvvm.data.model.UserDTO
import com.molyavin.mvvm.domain.usecase.base.IUseCase
import com.molyavin.mvvm.utils.MissingUserInfoException
import javax.inject.Inject

class  MapFirebaseUserIntoUserDTOUseCase @Inject constructor() : IUseCase<FirebaseUser?, UserDTO> {

    override fun execute(income: FirebaseUser?): UserDTO {
        return income?.let { user ->
            UserDTO(
                id = user.uid,
                email = user.email!!,
                creationTime = user.metadata!!.creationTimestamp,
                lastLoginTime = user.metadata!!.lastSignInTimestamp,
                urlImage = "https://ichef.bbci.co.uk/news/800/cpsprodpb/11097/production/_109238796_gettyimages-450997371.jpg"
            )
        } ?: throw MissingUserInfoException()
    }
}