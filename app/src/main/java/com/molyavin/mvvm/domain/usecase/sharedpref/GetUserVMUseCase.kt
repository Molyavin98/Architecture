package com.molyavin.mvvm.domain.usecase.sharedpref

import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.domain.models.UserVM
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import com.molyavin.mvvm.domain.usecase.utils.ConvertMillisecondInDateUseCase
import javax.inject.Inject

class GetUserVMUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val millisecondInDateUseCase: ConvertMillisecondInDateUseCase,
) : IAsyncUseCase<Any?, UserVM> {
    override suspend fun execute(income: Any?): UserVM {

        val userDTOInfo = userRepository.getUserDTO()

        val creationDate = millisecondInDateUseCase.execute(userDTOInfo.creationTime)
        val lastLoginDate = millisecondInDateUseCase.execute(userDTOInfo.lastLoginTime)

        return UserVM(
            id = userDTOInfo.id,
            email = userDTOInfo.email,
            creationTime = creationDate,
            lastLoginTime = lastLoginDate,
            urlImage = userDTOInfo.urlImage,
        )
    }
}

