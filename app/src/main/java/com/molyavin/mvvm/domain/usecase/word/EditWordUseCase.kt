package com.molyavin.mvvm.domain.usecase.word

import com.molyavin.mvvm.data.network.ApiServiceRetrofit
import com.molyavin.mvvm.domain.models.WordVM
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import com.molyavin.mvvm.domain.usecase.sharedpref.GetWordVMUseCase
import javax.inject.Inject

class EditWordUseCase @Inject constructor(
    private val apiServiceRetrofit: ApiServiceRetrofit,
    private val getWordVMUseCase: GetWordVMUseCase,
    private val getWordUseCase: GetWordUseCase,

    ) : IAsyncUseCase<WordVM, Unit> {

    override suspend fun execute(income: WordVM) {
        try {
            val id = getWordVMUseCase.execute(null)
            val word = getWordUseCase.execute(id)
            apiServiceRetrofit.editWord(word.id.toString(), income)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}