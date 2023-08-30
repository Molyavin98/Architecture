package com.molyavin.mvvm.domain.usecase.onboarding

import com.molyavin.mvvm.data.repositories.SlideRepository
import com.molyavin.mvvm.domain.mapper.SlideMapper
import com.molyavin.mvvm.domain.models.SlideVM
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import javax.inject.Inject

class SlideMapperUseCase @Inject constructor(
    private val slideRepository: SlideRepository,
    private val slideMapper: SlideMapper
) : IAsyncUseCase<Unit, List<SlideVM>> {

    override suspend fun execute(income: Unit): List<SlideVM> {
        val slideEntityList = slideRepository.getSlides()
        return slideEntityList.map { slideMapper.mapEntityToVM(it) }
    }

}