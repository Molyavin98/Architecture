package com.molyavin.mvvm.domain.usecase.sharedpref

import com.google.firebase.auth.FirebaseUser
import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.domain.usecase.auth.MapFirebaseUserIntoUserDTOUseCase
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import javax.inject.Inject

class SaveUserVMUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val mapFirebaseUserIntoUserDTOUseCase: MapFirebaseUserIntoUserDTOUseCase,
) :
    IAsyncUseCase<FirebaseUser?, Unit> {
    override suspend fun execute(income: FirebaseUser?) {
        userRepository.saveUserDTO(mapFirebaseUserIntoUserDTOUseCase.execute(income))
    }

}