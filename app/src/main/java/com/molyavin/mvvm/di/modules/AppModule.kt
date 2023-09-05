package com.molyavin.mvvm.di.modules

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.molyavin.mvvm.data.network.ApiService
import com.molyavin.mvvm.data.repositories.SettingRepository
import com.molyavin.mvvm.data.repositories.SettingRepositoryImpl
import com.molyavin.mvvm.data.repositories.SlideRepository
import com.molyavin.mvvm.data.repositories.SlideRepositoryImpl
import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.data.repositories.UserRepositoryImpl
import com.molyavin.mvvm.data.storage.DBSharedPreference
import com.molyavin.mvvm.di.scope.AppScope
import com.molyavin.mvvm.domain.mapper.SlideMapper
import com.molyavin.mvvm.utils.Constants
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    @AppScope
    fun provideContext() = context

    @Provides
    @AppScope
    fun provideDBSharedPreference(): DBSharedPreference {
        return DBSharedPreference(context)
    }

    @Provides
    @AppScope
    fun provideOnBoardingRepository(dbSharedPreference: DBSharedPreference): SettingRepository =
        SettingRepositoryImpl(dbSharedPreference)

    @Provides
    @AppScope
    fun provideApiService(): ApiService = ApiService()

    @Provides
    @AppScope
    fun provideSlideMapper(): SlideMapper = SlideMapper()

    @Provides
    @AppScope
    fun provideSlideRepository(apiService: ApiService): SlideRepository =
        SlideRepositoryImpl(apiService)

    @Provides
    @AppScope
    fun provideFirebaseAunt() = FirebaseAuth.getInstance()

    @Provides
    @AppScope
    fun provideFirebaseRemoteConfig(): FirebaseRemoteConfig {
        val instance = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(Constants.MINIMUM_FETCH_INTERVAL_SECONDS)
            .build()
        instance.setConfigSettingsAsync(configSettings)

        return instance
    }

    @Provides
    @AppScope
    fun provideDataBase(
        firebaseAuth: FirebaseAuth,
        dbSharedPreference: DBSharedPreference
    ): UserRepository =
        UserRepositoryImpl(
            fireBaseAunt = firebaseAuth,
            dbSharedPreference = dbSharedPreference
        )

}