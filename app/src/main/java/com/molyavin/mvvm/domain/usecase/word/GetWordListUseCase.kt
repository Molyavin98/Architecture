package com.molyavin.mvvm.domain.usecase.word

import com.molyavin.mvvm.domain.models.WordVM
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import javax.inject.Inject

class GetWordListUseCase @Inject constructor(
    private val mapFireBaseWordsDTOUseCase: MapFireBaseWordsDTOUseCase
) : IAsyncUseCase<Any?, List<WordVM>> {

    override suspend fun execute(incom: Any?): List<WordVM> {
        return mapFireBaseWordsDTOUseCase.execute(null)
    }
}
