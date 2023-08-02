package com.molyavin.mvvm.domain.di

import android.app.Application
import com.molyavin.mvvm.domain.di.component.Injector

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.inject(this)
    }

}