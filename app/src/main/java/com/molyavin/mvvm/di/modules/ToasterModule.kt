package com.molyavin.mvvm.di.modules

import com.molyavin.mvvm.di.scope.AppScope
import com.molyavin.mvvm.utils.Toaster
import com.molyavin.mvvm.utils.ToasterImpl
import dagger.Module
import dagger.Provides

@Module
class ToasterModule {

    @Provides
    @AppScope
    fun provideToaster(toasterImpl: ToasterImpl): Toaster = toasterImpl
}