package com.molyavin.mvvm.domain.di.modules

import android.app.Application
import android.content.Context
import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.data.repositories.UserRepositoryImpl
import com.molyavin.mvvm.data.storage.DBSharedPreference
import com.molyavin.mvvm.domain.di.scope.AppScope
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

}