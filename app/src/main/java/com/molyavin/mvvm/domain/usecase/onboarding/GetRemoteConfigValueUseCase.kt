package com.molyavin.mvvm.domain.usecase.onboarding

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.molyavin.mvvm.domain.usecase.base.IUseCase
import javax.inject.Inject

class GetRemoteConfigValueUseCase @Inject constructor(
    private val firebaseRemoteConfig: FirebaseRemoteConfig
) : IUseCase<String, Boolean> {

    override fun execute(income: String): Boolean {

        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(20)
            .build()
        firebaseRemoteConfig.setConfigSettingsAsync(configSettings)

        firebaseRemoteConfig.fetch().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseRemoteConfig.activate()
            } else {
                Log.d("RemoteConfigException", task.exception.toString())
            }
        }

        return firebaseRemoteConfig.getBoolean(income)
    }
}