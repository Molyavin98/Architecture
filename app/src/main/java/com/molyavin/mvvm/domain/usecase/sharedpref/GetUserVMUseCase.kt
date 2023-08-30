package com.molyavin.mvvm.domain.usecase.sharedpref

import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.domain.models.UserVM
import com.molyavin.mvvm.domain.models.toVM
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import javax.inject.Inject

class GetUserVMUseCase @Inject constructor(
    private val userRepository: UserRepository,
) : IAsyncUseCase<Any?,UserVM>{
    override suspend fun execute(income: Any?): UserVM {
        return userRepository.getUserDTO().toVM()
    }
}