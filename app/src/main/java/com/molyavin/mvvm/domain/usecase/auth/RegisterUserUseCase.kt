package com.molyavin.mvvm.domain.usecase.auth

import com.molyavin.mvvm.data.model.toDTO
import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.domain.models.NewUserVM
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import com.molyavin.mvvm.utils.AppDispatchers
import com.molyavin.mvvm.utils.IncorrectUserInfoException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume

class RegisterUserUseCase @Inject constructor(
    private val validateRegisterUserInfoUseCase: ValidateRegisterUserInfoUseCase,
    private val fireBaseRepository: UserRepository,
    private val dispatchers: AppDispatchers
) : IAsyncUseCase<NewUserVM, Boolean> {

    override suspend fun execute(income: NewUserVM): Boolean {
        return withContext(dispatchers.io) {
            suspendCancellableCoroutine {
                if (!validateRegisterUserInfoUseCase.execute(income)) {
                    it.cancel(IncorrectUserInfoException())
                    return@suspendCancellableCoroutine
                }
                fireBaseRepository.registerUser(income.toDTO())
                    .addOnCompleteListener { task ->
                        if (task.exception != null) {
                            it.cancel(task.exception)
                            return@addOnCompleteListener
                        }
                        it.resume(task.isSuccessful)
                    }
            }
        }
    }

}