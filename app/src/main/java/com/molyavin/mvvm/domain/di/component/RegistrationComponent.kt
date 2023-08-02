package com.molyavin.mvvm.domain.di.component

import com.molyavin.mvvm.domain.di.modules.RegistrationModule
import com.molyavin.mvvm.domain.di.scope.ActivityScope
import com.molyavin.mvvm.presentation.RegistrationActivity
import dagger.Component
import dagger.Subcomponent

@ActivityScope
@Component(modules = [RegistrationModule::class])
interface RegistrationComponent {

    fun inject(activity: RegistrationActivity)

}