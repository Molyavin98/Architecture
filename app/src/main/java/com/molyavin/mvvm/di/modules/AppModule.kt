package com.molyavin.mvvm.di.modules

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.molyavin.mvvm.data.network.ApiService
import com.molyavin.mvvm.data.network.ApiServiceInfoApp
import com.molyavin.mvvm.data.repositories.InfoAppRepository
import com.molyavin.mvvm.data.repositories.InfoAppRepositoryImpl
import com.molyavin.mvvm.data.repositories.SettingRepository
import com.molyavin.mvvm.data.repositories.SettingRepositoryImpl
import com.molyavin.mvvm.data.repositories.SlideRepository
import com.molyavin.mvvm.data.repositories.SlideRepositoryImpl
import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.data.repositories.UserRepositoryImpl
import com.molyavin.mvvm.data.repositories.WordRepository
import com.molyavin.mvvm.data.repositories.WordRepositoryImpl
import com.molyavin.mvvm.data.storage.DBSharedPreference
import com.molyavin.mvvm.di.scope.AppScope
import com.molyavin.mvvm.domain.mapper.SlideMapper
import com.molyavin.mvvm.utils.AppDispatchers
import com.molyavin.mvvm.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
    fun provideWordRepository(dbSharedPreference: DBSharedPreference): WordRepository =
        WordRepositoryImpl(dbSharedPreference)

    @Provides
    @AppScope
    fun provideSlideRepository(apiService: ApiService): SlideRepository =
        SlideRepositoryImpl(apiService)

    @Provides
    @AppScope
    fun provideAppDispatchers() = AppDispatchers()

    @Provides
    @AppScope
    fun provideApiService(): ApiService = ApiService()

    @Provides
    @AppScope
    fun provideApiServiceInfoApp(): ApiServiceInfoApp = ApiServiceInfoApp()


    @Provides
    @AppScope
    fun provideInfoRepository(apiServiceInfoApp: ApiServiceInfoApp):
            InfoAppRepository = InfoAppRepositoryImpl(apiServiceInfoApp)

    @Provides
    @AppScope
    fun provideFirebaseAunt() = FirebaseAuth.getInstance()

    @Provides
    @AppScope
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @AppScope
<<<<<<< HEAD
=======
    fun provideSlideMapper(): SlideMapper = SlideMapper()

    @Provides
    @AppScope
>>>>>>> 0858822 (Implementation epoxy lib in project)
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
    fun provideAppDispatchers() = AppDispatchers()

    @Provides
    @AppScope
    fun provideDataBase(
        firebaseAuth: FirebaseAuth,
        moshi: Moshi,
        dbSharedPreference: DBSharedPreference
    ): UserRepository =
        UserRepositoryImpl(
            fireBaseAunt = firebaseAuth,
            moshi = moshi,
            dbSharedPreference = dbSharedPreference
        )

}