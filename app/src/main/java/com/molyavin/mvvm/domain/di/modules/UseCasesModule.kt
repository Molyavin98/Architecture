package com.molyavin.mvvm.domain.di.modules

import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.domain.di.scope.AppScope
import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.domain.usecase.CheckFieldUseCase
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase
import com.molyavin.mvvm.domain.usecase.SaveUserInfoUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    @AppScope
    fun provideReadUserInfoUseCase(userRepository: UserRepository): ReadUserInfoUseCase =
        ReadUserInfoUseCase(userRepository)

    @Provides
    @AppScope
    fun provideSaveUserInfoUseCase(userRepository: UserRepository): SaveUserInfoUseCase =
        SaveUserInfoUseCase(userRepository)

    @Provides
    @AppScope
    fun provideCheckFieldUseCase(userRepository: UserRepository): CheckFieldUseCase =
        CheckFieldUseCase(userRepository)


}