package com.molyavin.mvvm.domain.di.component

import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.domain.di.MyApplication
import com.molyavin.mvvm.domain.di.modules.AppModule
import com.molyavin.mvvm.domain.di.scope.AppScope
import com.molyavin.mvvm.presentation.AuthorizationActivity
import com.molyavin.mvvm.presentation.RegistrationActivity
import com.molyavin.mvvm.presenter.AuthorizationPresenter
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun getUserRepository(): UserRepository

}