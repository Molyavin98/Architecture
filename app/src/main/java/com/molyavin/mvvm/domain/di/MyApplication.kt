package com.molyavin.mvvm.domain.di

import android.app.Application
import com.molyavin.mvvm.domain.di.component.AppComponent
import com.molyavin.mvvm.domain.di.component.DaggerAppComponent
import com.molyavin.mvvm.domain.di.modules.AppModule

class MyApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}