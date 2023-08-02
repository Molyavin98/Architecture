package com.molyavin.mvvm.domain.di.component

import com.molyavin.mvvm.domain.di.MyApplication
import com.molyavin.mvvm.domain.di.modules.AppModule

object Injector {

    lateinit var INSTANCE: AppComponent

    fun inject(application: MyApplication) {
        INSTANCE = DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .build()
    }

}