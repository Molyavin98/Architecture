package com.molyavin.mvvm.domain.usecase.word

import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import javax.inject.Inject

class GetWordUseCase @Inject constructor(
    private val mapFireBaseWordsDTOUseCase: MapFireBaseWordsDTOUseCase
) : IAsyncUseCase<Int?, Any?> {

    override suspend fun execute(incom: Int?): Any? {

        val response = mapFireBaseWordsDTOUseCase.execute(null)

        if (incom != null) {
            return response?.get(incom)
        }

        return response
    }
}
