package com.molyavin.mvvm.domain.di.modules

import android.content.Context
import android.os.Handler
import com.molyavin.mvvm.data.repositories.SettingRepository
import com.molyavin.mvvm.data.repositories.SettingRepositoryImpl
import com.molyavin.mvvm.data.repositories.SlideRepository
import com.molyavin.mvvm.data.repositories.SlideRepositoryImpl
import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.data.repositories.UserRepositoryImpl
import com.molyavin.mvvm.data.storage.ApiService
import com.molyavin.mvvm.data.storage.DBSharedPreference
import com.molyavin.mvvm.domain.di.scope.AppScope
import com.molyavin.mvvm.domain.usecase.SlideMapper
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
    fun provideUserRepository(dbSharedPreference: DBSharedPreference): UserRepository =
        UserRepositoryImpl(dbSharedPreference)

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
    fun provideSlideRepository(apiService: ApiService, slideMapper: SlideMapper): SlideRepository =
        SlideRepositoryImpl(apiService, slideMapper)

    @Provides
    @AppScope
    fun provideHandler(): Handler = Handler()


}