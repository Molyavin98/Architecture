package com.molyavin.mvvm.domain.usecase.onboarding

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.molyavin.mvvm.domain.usecase.base.IUseCase
import com.molyavin.mvvm.utils.AppDispatchers
import io.reactivex.Single
import javax.inject.Inject

class GetRemoteConfigValueUseCase @Inject constructor(
    private val firebaseRemoteConfig: FirebaseRemoteConfig,
    private val dispatchers: AppDispatchers,
) : IUseCase<String, Single<Boolean>> {

    override fun execute(income: String) = Single.create<Boolean> { emitter ->
        try {
            val fetchTask = firebaseRemoteConfig.fetch()
            fetchTask.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseRemoteConfig.activate()
                    val value = firebaseRemoteConfig.getBoolean(income)
                    emitter.onSuccess(value)
                } else {
                    emitter.onError(Exception("Fetch failed"))
                }
            }
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }.subscribeOn(dispatchers.rxIo)

}