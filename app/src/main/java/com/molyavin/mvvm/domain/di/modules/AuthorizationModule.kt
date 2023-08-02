package com.molyavin.mvvm.domain.di.modules

import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.domain.di.scope.ActivityScope
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase
import com.molyavin.mvvm.presenter.AuthorizationPresenter
import com.molyavin.mvvm.presenter.Presenter
import dagger.Module
import dagger.Provides

@Module
class AuthorizationModule {

    @Provides
    @ActivityScope
    fun provideReadUserInfoUseCase(userRepository: UserRepository): ReadUserInfoUseCase =
        ReadUserInfoUseCase(userRepository)

    @Provides
    @ActivityScope
    fun provideAuthorizationPresenter(userRepository: UserRepository): Presenter.Authorization =
        AuthorizationPresenter(ReadUserInfoUseCase(userRepository))

}