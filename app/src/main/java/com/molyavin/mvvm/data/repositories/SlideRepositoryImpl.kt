package com.molyavin.mvvm.data.repositories

import com.molyavin.mvvm.data.storage.ApiService
import com.molyavin.mvvm.domain.models.SlideEntity
import com.molyavin.mvvm.domain.models.SlideVM
import com.molyavin.mvvm.domain.usecase.SlideMapper

class SlideRepositoryImpl(
    private val apiService: ApiService,
) : SlideRepository {
    override suspend fun getSlides(): List<SlideEntity> {
        return apiService.fetchSlides()
    }
}
