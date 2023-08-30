package com.molyavin.mvvm.di

import android.content.Context
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.di.component.AppComponent
import com.molyavin.mvvm.di.component.DaggerAppComponent
import com.molyavin.mvvm.di.modules.AppModule
import com.molyavin.mvvm.di.modules.NavigationModule

object Injector {

    lateinit var INSTANCE: AppComponent

    fun inject(context: Context, router: Router) {
        INSTANCE = DaggerAppComponent.builder()
            .appModule(AppModule(context))
            .navigationModule(NavigationModule(router))
            .build()
    }

}