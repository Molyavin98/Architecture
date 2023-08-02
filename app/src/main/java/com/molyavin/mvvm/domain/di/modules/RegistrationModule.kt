package com.molyavin.mvvm.domain.di.modules

import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.domain.di.scope.ActivityScope
import com.molyavin.mvvm.domain.usecase.SaveUserInfoUseCase
import com.molyavin.mvvm.viewmodel.RegistrationViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class RegistrationModule{

    @Provides
    @ActivityScope
    fun provideSaveUserInfoUseCase(userRepository: UserRepository): SaveUserInfoUseCase =
        SaveUserInfoUseCase(userRepository)

    @Provides
    @ActivityScope
    fun provideRegistrationViewModelFactory(saveUserInfoUseCase: SaveUserInfoUseCase): RegistrationViewModelFactory =
        RegistrationViewModelFactory(saveUserInfoUseCase)

}