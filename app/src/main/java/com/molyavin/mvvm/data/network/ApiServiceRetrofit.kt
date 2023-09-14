package com.molyavin.mvvm.data.network

import com.molyavin.mvvm.data.model.ProductDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceRetrofit {

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: String): ProductDTO
}