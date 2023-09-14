package com.molyavin.mvvm.di.modules

import com.molyavin.mvvm.data.network.ApiServiceRetrofit
import com.molyavin.mvvm.di.scope.AppScope
import com.molyavin.mvvm.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @AppScope
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    @AppScope
    @Provides
    fun provideApiServiceRetrofit(retrofit: Retrofit): ApiServiceRetrofit =
        retrofit.create(ApiServiceRetrofit::class.java)

    @AppScope
    @Provides
    fun provideOkhttp(): OkHttpClient = OkHttpClient.Builder().build()

}