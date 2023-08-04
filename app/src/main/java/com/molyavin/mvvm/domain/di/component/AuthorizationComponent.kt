package com.molyavin.mvvm.domain.di.component

import com.molyavin.mvvm.controlles.AuthorizationController
import com.molyavin.mvvm.domain.di.modules.AuthorizationModule
import com.molyavin.mvvm.domain.di.scope.ActivityScope
import dagger.Component


@ActivityScope
@Component(modules = [AuthorizationModule::class])
interface AuthorizationComponent {

    fun inject(activity: AuthorizationController)
}