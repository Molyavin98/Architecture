package com.molyavin.mvvm.domain.usecase.word

import com.molyavin.mvvm.data.network.ApiServiceRetrofit
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import javax.inject.Inject

class DeleteWordUseCase @Inject constructor(private val apiServiceRetrofit: ApiServiceRetrofit) :
    IAsyncUseCase<String, Unit> {

    override suspend fun execute(income: String) {
        try {
            apiServiceRetrofit.deleteWord(id = income)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}