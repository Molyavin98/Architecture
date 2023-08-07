package com.molyavin.mvvm.domain.di.modules

import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.domain.di.scope.ActivityScope
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase
import com.molyavin.mvvm.presentation.screens.login.presenter.AuthorizationPresenter
import com.molyavin.mvvm.presentation.screens.login.presenter.Presenter
import dagger.Module
import dagger.Provides

@Module
class AuthorizationModule(private val userRepository: UserRepository) {

    @Provides
    @ActivityScope
    fun provideReadUserInfoUseCase(): ReadUserInfoUseCase = ReadUserInfoUseCase(userRepository)

    @Provides
    @ActivityScope
    fun provideAuthorizationPresenter(useCase: ReadUserInfoUseCase): Presenter.Authorization =
        AuthorizationPresenter(useCase)

}