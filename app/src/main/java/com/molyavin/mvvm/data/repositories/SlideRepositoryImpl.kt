package com.molyavin.mvvm.data.repositories

import com.molyavin.mvvm.data.model.SlideDTO
import com.molyavin.mvvm.data.network.ApiService

class SlideRepositoryImpl(
    private val apiService: ApiService,
) : SlideRepository {
    override suspend fun getSlides(): List<SlideDTO> {
        return apiService.fetchSlides()
    }
}
