package com.molyavin.mvvm.domain.usecase.word

import com.molyavin.mvvm.data.network.ApiServiceRetrofit
import com.molyavin.mvvm.domain.models.WordVM
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import javax.inject.Inject

class PostAddWordUseCase @Inject constructor(
    private val apiServiceRetrofit: ApiServiceRetrofit,
) : IAsyncUseCase<WordVM, Unit> {

    override suspend fun execute(income: WordVM) {
        try {
            apiServiceRetrofit.postWord(income)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}