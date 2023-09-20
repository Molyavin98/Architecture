package com.molyavin.mvvm.domain.usecase.sharedpref

import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.data.shared.UserDTOKeys.CREATION_TIME_KEY
import com.molyavin.mvvm.data.shared.UserDTOKeys.EMAIL_KEY
import com.molyavin.mvvm.data.shared.UserDTOKeys.ID_KEY
import com.molyavin.mvvm.data.shared.UserDTOKeys.LAST_LOGIN_TIME_KEY
import com.molyavin.mvvm.data.shared.UserDTOKeys.URL_IMAGE_KEY
import com.molyavin.mvvm.domain.models.UserVM
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import com.molyavin.mvvm.domain.usecase.utils.ConvertMillisecondInDateUseCase
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class GetUserVMUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val millisecondInDateUseCase: ConvertMillisecondInDateUseCase,

    ) : IAsyncUseCase<Any?, UserVM> {
    override suspend fun execute(income: Any?): UserVM {

        val getUserDTOInfo = userRepository.getUserDTO().firstOrNull() ?: HashMap()

        val creationDate = millisecondInDateUseCase.execute(getUserDTOInfo[CREATION_TIME_KEY]!!.toLong())
        val lastLoginDate = millisecondInDateUseCase.execute(getUserDTOInfo[LAST_LOGIN_TIME_KEY]!!.toLong())

        return UserVM(
            id = getUserDTOInfo[ID_KEY] ?: "",
            email = getUserDTOInfo[EMAIL_KEY] ?: "",
            creationTime = creationDate,
            lastLoginTime = lastLoginDate,
            urlImage = getUserDTOInfo[URL_IMAGE_KEY] ?: ""
        )
    }
}

