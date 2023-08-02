package com.molyavin.mvvm.domain.di.component

import com.molyavin.mvvm.domain.di.modules.AppModule
import com.molyavin.mvvm.domain.di.scope.AppScope
import com.molyavin.mvvm.presentation.AuthorizationActivity
import com.molyavin.mvvm.presentation.RegistrationActivity
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: RegistrationActivity)
    fun inject(activity: AuthorizationActivity)

}