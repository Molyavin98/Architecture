package com.molyavin.mvvm.di.modules

import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class NavigationModule(private val router: Router) {

    @AppScope
    @Provides
    fun provideRouter(): Router {
        return router
    }

}