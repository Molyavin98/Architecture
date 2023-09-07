package com.molyavin.mvvm.domain.usecase.onboarding

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.molyavin.mvvm.domain.usecase.base.IUseCase
import com.molyavin.mvvm.utils.AppDispatchers
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetRemoteConfigValueUseCase @Inject constructor(
    private val firebaseRemoteConfig: FirebaseRemoteConfig,
    private val dispatchers: AppDispatchers,
) : IUseCase<String, Flow<Boolean>> {

    override fun execute(income: String): Flow<Boolean> = flow {
        try {
            val fetchTask = firebaseRemoteConfig.fetch()
            fetchTask.await()

            if (fetchTask.isSuccessful) {
                firebaseRemoteConfig.activate()
                val value = firebaseRemoteConfig.getBoolean(income)
                emit(value)
            } else {
                currentCoroutineContext().cancel(CancellationException("Fetch failed", null))
            }
        } catch (e: Exception) {
            currentCoroutineContext().cancel(CancellationException("Fetch failed", e))
        }
    }.flowOn(dispatchers.io)

}