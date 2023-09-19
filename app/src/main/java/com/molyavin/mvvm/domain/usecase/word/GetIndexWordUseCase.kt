package com.molyavin.mvvm.domain.usecase.word

import com.molyavin.mvvm.domain.models.WordVM
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import com.molyavin.mvvm.domain.usecase.sharedpref.GetWordVMUseCase
import javax.inject.Inject

class GetIndexWordUseCase @Inject constructor(
    private val getWordVMUseCase: GetWordVMUseCase,
    private val getWordUseCase: GetWordUseCase,
) :
    IAsyncUseCase<Any?, WordVM?> {

    override suspend fun execute(income: Any?): WordVM {

        val id = getWordVMUseCase.execute(null)
        val word: WordVM = getWordUseCase.execute(id) as WordVM

        return WordVM(id = word.id, eng = word.eng, ua = word.ua)

    }
}