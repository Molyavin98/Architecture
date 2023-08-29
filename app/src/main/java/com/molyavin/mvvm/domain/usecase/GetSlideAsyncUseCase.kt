package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.data.repositories.SlideRepository
import com.molyavin.mvvm.domain.models.SlideVM
import javax.inject.Inject

class GetSlideAsyncUseCase @Inject constructor(private val slideRepository: SlideRepository) :
    IAsyncUseCase<Any?, List<SlideVM>> {
    override suspend fun execute(income: Any?): List<SlideVM> {
        return slideRepository.getSlides()
    }


}