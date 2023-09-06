package com.molyavin.mvvm.domain.usecase.onboarding

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.molyavin.mvvm.domain.usecase.base.IUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetRemoteConfigValueUseCase @Inject constructor(
    private val firebaseRemoteConfig: FirebaseRemoteConfig,
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
                throw Exception("Fetch failed")
            }
        } catch (e: Exception) {
            throw e
        }
    }.flowOn(Dispatchers.IO)

}