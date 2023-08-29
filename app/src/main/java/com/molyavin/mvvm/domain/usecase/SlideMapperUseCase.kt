package com.molyavin.mvvm.domain.usecase

import com.molyavin.mvvm.data.repositories.SlideRepository
import com.molyavin.mvvm.domain.models.SlideVM

class SlideMapperUseCase(
    private val slideRepository: SlideRepository,
    private val slideMapper: SlideMapper
) : IAsyncUseCase<Unit, List<SlideVM>> {

    override suspend fun execute(income: Unit): List<SlideVM> {
        val slideEntityList = slideRepository.getSlides()
        return slideEntityList.map { slideMapper.mapEntityToVM(it) }
    }


}