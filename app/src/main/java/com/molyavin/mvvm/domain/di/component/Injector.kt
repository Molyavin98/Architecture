package com.molyavin.mvvm.domain.di.component

import android.content.Context
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.domain.di.modules.AppModule
import com.molyavin.mvvm.domain.di.modules.NavigationModule

object Injector {

    lateinit var INSTANCE: AppComponent

    fun inject(context: Context, router: Router) {
        INSTANCE = DaggerAppComponent.builder()
            .appModule(AppModule(context))
            .navigationModule(NavigationModule(router))
            .build()
    }

}