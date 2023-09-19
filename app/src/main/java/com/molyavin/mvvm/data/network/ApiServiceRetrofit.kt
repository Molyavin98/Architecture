package com.molyavin.mvvm.data.network

import com.molyavin.mvvm.data.model.WordDTO
import com.molyavin.mvvm.domain.models.WordVM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiServiceRetrofit {

    @GET("words.json")
    suspend fun getWord(): Response<Map<String, WordDTO>>

    @POST("words.json")
    suspend fun postWord(@Body wordVM: WordVM): Response<WordDTO>

    @DELETE("words/{id}.json")
    suspend fun deleteWord(@Path("id") id: String): Response<Void>

    @PUT("words/{id}.json")
    suspend fun editWord(@Path("id") id: String, @Body wordVM: WordVM): Response<WordDTO>
}
