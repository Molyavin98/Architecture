package com.molyavin.mvvm.domain.di.component

import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.domain.di.modules.AppModule
import com.molyavin.mvvm.domain.di.scope.AppScope
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun getUserRepository(): UserRepository

}