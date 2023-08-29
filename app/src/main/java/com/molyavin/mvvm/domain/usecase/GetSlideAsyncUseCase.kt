package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.data.repositories.SlideRepository
import com.molyavin.mvvm.data.storage.ApiService
import com.molyavin.mvvm.domain.models.SlideEntity
import com.molyavin.mvvm.domain.models.SlideVM
import javax.inject.Inject

class GetSlideAsyncUseCase @Inject constructor(private val slideRepository: SlideRepository) :
    IAsyncUseCase<Any?, List<SlideEntity>> {
    override suspend fun execute(income: Any?): List<SlideEntity> {
        return slideRepository.getSlides()
    }


}