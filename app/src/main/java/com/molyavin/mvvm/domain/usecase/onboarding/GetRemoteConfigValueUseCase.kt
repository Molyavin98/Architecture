package com.molyavin.mvvm.domain.usecase.onboarding

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class GetRemoteConfigValueUseCase @Inject constructor(
    private val firebaseRemoteConfig: FirebaseRemoteConfig,
) : IAsyncUseCase<String, Boolean> {

    override suspend fun execute(income: String): Boolean = suspendCoroutine { continuation ->

        firebaseRemoteConfig.fetch().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseRemoteConfig.activate()
                val value = firebaseRemoteConfig.getBoolean(income)
                continuation.resume(value)
            } else {
                continuation.resumeWithException(task.exception ?: Exception("Fetch failed"))
            }
        }
    }


}