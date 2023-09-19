package com.molyavin.mvvm.domain.usecase.word

import com.molyavin.mvvm.domain.models.WordVM
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import javax.inject.Inject

class GetWordUseCase @Inject constructor(
    private val mapFireBaseWordsDTOUseCase: MapFireBaseWordsDTOUseCase
) : IAsyncUseCase<Int, WordVM> {

    override suspend fun execute(incom: Int): WordVM {

        val wordList = mapFireBaseWordsDTOUseCase.execute(null)
        return wordList?.get(incom) ?: WordVM.empty()
    }
}
