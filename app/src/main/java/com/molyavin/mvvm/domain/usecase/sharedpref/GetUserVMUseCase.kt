package com.molyavin.mvvm.domain.usecase.sharedpref

import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.domain.models.UserVM
import com.molyavin.mvvm.domain.models.toVM
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import com.molyavin.mvvm.domain.usecase.utils.ConvertMillisecondInDateUseCase
import javax.inject.Inject

class GetUserVMUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val convertMillisecondInDateUseCase: ConvertMillisecondInDateUseCase,

    ) : IAsyncUseCase<Any?,UserVM>{
    override suspend fun execute(income: Any?): UserVM {
        val userDto = userRepository.getUserDTO()
        val creationDate = convertMillisecondInDateUseCase.execute(userDto.creationTime)
        val lastLoginDate = convertMillisecondInDateUseCase.execute(userDto.lastLoginTime)
        return userDto.toVM(creationTime = creationDate, lastLoginTime = lastLoginDate)
    }
}