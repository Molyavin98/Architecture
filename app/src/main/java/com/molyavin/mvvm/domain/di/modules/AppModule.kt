package com.molyavin.mvvm.domain.di.modules

import android.app.Application
import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.data.repositories.UserRepositoryImpl
import com.molyavin.mvvm.data.storage.DBSharedPreference
import com.molyavin.mvvm.domain.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @Provides
    @AppScope
    fun provideApplication(): Application = application

    @Provides
    @AppScope
    fun provideDBSharedPreference(): DBSharedPreference {
        return DBSharedPreference(application.applicationContext)
    }

    @Provides
    @AppScope
    fun provideUserRepository(dbSharedPreference: DBSharedPreference): UserRepository =
        UserRepositoryImpl(dbSharedPreference)

}