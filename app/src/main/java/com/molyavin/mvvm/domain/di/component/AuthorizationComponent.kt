package com.molyavin.mvvm.domain.di.component

import com.molyavin.mvvm.domain.di.modules.AuthorizationModule
import com.molyavin.mvvm.domain.di.scope.ActivityScope
import com.molyavin.mvvm.presentation.AuthorizationActivity
import dagger.Component
import dagger.Subcomponent


@ActivityScope
@Component(modules = [AuthorizationModule::class])
interface AuthorizationComponent {

    fun inject(activity: AuthorizationActivity)
}