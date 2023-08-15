package com.molyavin.mvvm.domain.di.component

import com.molyavin.mvvm.MainActivity
import com.molyavin.mvvm.presentation.screens.authorization.screen.AuthorizationController
import com.molyavin.mvvm.presentation.screens.registration.screen.RegistrationController
import com.molyavin.mvvm.presentation.screens.profile.screen.ProfileController
import com.molyavin.mvvm.domain.di.modules.AppModule
import com.molyavin.mvvm.domain.di.modules.NavigationModule
import com.molyavin.mvvm.domain.di.modules.ToasterModule
import com.molyavin.mvvm.domain.di.modules.UseCasesModule
import com.molyavin.mvvm.domain.di.scope.AppScope
import com.molyavin.mvvm.presentation.screens.menu.screen.MenuController
import com.molyavin.mvvm.presentation.screens.onboarding.screen.OnBoardingController
import com.molyavin.mvvm.presentation.screens.splashscreen.screen.SplashScreenController
import dagger.Component

@AppScope
@Component(modules = [AppModule::class, UseCasesModule::class, NavigationModule::class, ToasterModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(controller: AuthorizationController)
    fun inject(controller: ProfileController)
    fun inject(controller: RegistrationController)
    fun inject(controller: SplashScreenController)
    fun inject(controller: OnBoardingController)
    fun inject(controller: MenuController)

}