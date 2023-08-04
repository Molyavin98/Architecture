package com.molyavin.mvvm.domain.di.component

import com.molyavin.mvvm.controlles.RegistrationController
import com.molyavin.mvvm.domain.di.modules.RegistrationModule
import com.molyavin.mvvm.domain.di.scope.ActivityScope
import dagger.Component

@ActivityScope
@Component(modules = [RegistrationModule::class])
interface RegistrationComponent {

    fun inject(activity: RegistrationController)

}