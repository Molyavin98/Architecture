package com.molyavin.mvvm.domain.di.component

import com.bluelinelabs.conductor.Controller
import com.molyavin.mvvm.MainActivity
import com.molyavin.mvvm.domain.di.modules.AppModule
import com.molyavin.mvvm.domain.di.modules.NavigationModule
import com.molyavin.mvvm.domain.di.modules.ToasterModule
import com.molyavin.mvvm.domain.di.modules.UseCasesModule
import com.molyavin.mvvm.domain.di.scope.AppScope
import com.molyavin.mvvm.presentation.viewmodels.AuthorizationViewModel
import com.molyavin.mvvm.presentation.viewmodels.MenuViewModel
import com.molyavin.mvvm.presentation.viewmodels.OnBoardingViewModel
import com.molyavin.mvvm.presentation.viewmodels.ProfileViewModel
import com.molyavin.mvvm.presentation.viewmodels.RegistrationViewModel
import com.molyavin.mvvm.presentation.viewmodels.SplashScreenViewModel
import com.molyavin.mvvm.presentation.viewmodels.SettingViewModel
import dagger.Component

@AppScope
@Component(modules = [AppModule::class, UseCasesModule::class, NavigationModule::class, ToasterModule::class])
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