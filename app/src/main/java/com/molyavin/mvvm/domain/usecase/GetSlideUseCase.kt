package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.data.repositories.SlideRepository
import com.molyavin.mvvm.domain.models.Slide
import javax.inject.Inject

class GetSlideUseCase @Inject constructor(private val slideRepository: SlideRepository) :
    IUseCase<Any?, List<Slide>> {

    override fun execute(income: Any?): List<Slide> {
        return slideRepository.getSlides()
    }


}