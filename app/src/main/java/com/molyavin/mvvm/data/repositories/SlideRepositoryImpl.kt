package com.molyavin.mvvm.data.repositories

import com.molyavin.mvvm.data.storage.ApiService
import com.molyavin.mvvm.domain.models.SlideVM
import com.molyavin.mvvm.domain.usecase.SlideMapper

class SlideRepositoryImpl(
    private val apiService: ApiService,
    private val slideMapper: SlideMapper
) : SlideRepository {
    override suspend fun getSlides(): List<SlideVM> {
        val slideEntityList = apiService.fetchSlides()
        return slideEntityList.map { slideMapper.mapEntityToVM(it) }
    }
}