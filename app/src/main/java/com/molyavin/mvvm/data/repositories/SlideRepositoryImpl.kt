package com.molyavin.mvvm.data.repositories

import com.molyavin.mvvm.data.network.ApiService
import com.molyavin.mvvm.data.model.SlideDTO

class SlideRepositoryImpl(
    private val apiService: ApiService,
) : SlideRepository {
    override suspend fun getSlides(): List<SlideDTO> {
        return apiService.fetchSlides()
    }
}
