package com.molyavin.mvvm.di.component

import com.bluelinelabs.conductor.Controller
import com.molyavin.mvvm.MainActivity
import com.molyavin.mvvm.di.modules.AppModule
import com.molyavin.mvvm.di.modules.NavigationModule
import com.molyavin.mvvm.di.modules.ToasterModule
import com.molyavin.mvvm.di.scope.AppScope
import com.molyavin.mvvm.presentation.viewmodels.auth.AuthorizationViewModel
import com.molyavin.mvvm.presentation.viewmodels.main.MenuViewModel
import com.molyavin.mvvm.presentation.viewmodels.onboarding.OnBoardingViewModel
import com.molyavin.mvvm.presentation.viewmodels.profile.ProfileViewModel
import com.molyavin.mvvm.presentation.viewmodels.auth.RegistrationViewModel
import com.molyavin.mvvm.presentation.viewmodels.splash.SplashScreenViewModel
import com.molyavin.mvvm.presentation.viewmodels.settings.SettingViewModel
import dagger.Component

@AppScope
@Component(modules = [AppModule::class, NavigationModule::class, ToasterModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(controller: Controller)

    fun provideSplashScreenViewModel(): SplashScreenViewModel
    fun provideAuthorizationViewModel(): AuthorizationViewModel
    fun provideRegistrationViewModel(): RegistrationViewModel
    fun provideMenuViewModel(): MenuViewModel
    fun provideOnBoardingViewModel(): OnBoardingViewModel
    fun provideProfileViewModel(): ProfileViewModel
    fun provideSettingViewModel(): SettingViewModel
}