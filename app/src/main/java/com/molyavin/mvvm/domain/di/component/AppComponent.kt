package com.molyavin.mvvm.domain.di.component

import com.molyavin.mvvm.MainActivity
import com.molyavin.mvvm.controlles.AuthorizationController
import com.molyavin.mvvm.controlles.RegistrationController
import com.molyavin.mvvm.controlles.ProfileController
import com.molyavin.mvvm.domain.di.modules.AppModule
import com.molyavin.mvvm.domain.di.modules.NavigationModule
import com.molyavin.mvvm.domain.di.modules.ToasterModule
import com.molyavin.mvvm.domain.di.modules.UseCasesModule
import com.molyavin.mvvm.domain.di.scope.AppScope
import dagger.Component

@AppScope
@Component(modules = [AppModule::class, UseCasesModule::class, NavigationModule::class, ToasterModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(controller: AuthorizationController)
    fun inject(controller: ProfileController)
    fun inject(controller: RegistrationController)

}