package com.molyavin.mvvm.domain.usecase.onboarding

import com.molyavin.mvvm.data.model.SlideDTO
import com.molyavin.mvvm.data.repositories.SlideRepository
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import javax.inject.Inject

class GetSlideAsyncUseCase @Inject constructor(private val slideRepository: SlideRepository) :
    IAsyncUseCase<Any?, List<SlideDTO>> {
    override suspend fun execute(income: Any?): List<SlideDTO> {
        return slideRepository.getSlides()
    }


}