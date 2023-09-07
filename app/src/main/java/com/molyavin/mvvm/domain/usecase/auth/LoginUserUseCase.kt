package com.molyavin.mvvm.domain.usecase.auth

import com.molyavin.mvvm.data.model.toDTO
import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.domain.models.NewUserVM
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import com.molyavin.mvvm.domain.usecase.sharedpref.SaveUserVMUseCase
import com.molyavin.mvvm.utils.AppDispatchers
import com.molyavin.mvvm.utils.IncorrectUserInfoException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume

class LoginUserUseCase @Inject constructor(
    private val validateLoginUserInfoUseCase: ValidateLoginUserInfoUseCase,
    private val fireBaseRepository: UserRepository,
    private val saveUserVMUseCase: SaveUserVMUseCase,
    private val dispatcher: AppDispatchers,
) : IAsyncUseCase<NewUserVM, Boolean> {
    override suspend fun execute(income: NewUserVM): Boolean {
        return withContext(dispatcher.io) {
            val task = suspendCancellableCoroutine {
                if (!validateLoginUserInfoUseCase.execute(income)) {
                    it.cancel(IncorrectUserInfoException())
                    return@suspendCancellableCoroutine
                }
                fireBaseRepository.loginUser(income.toDTO())
                    .addOnCompleteListener { task ->
                        if (task.exception != null) {
                            it.cancel(task.exception)
                            return@addOnCompleteListener
                        }
                        it.resume(task)
                    }
            }
            saveUserVMUseCase.execute(task.result.user)
            return@withContext task.isSuccessful
        }
    }
}