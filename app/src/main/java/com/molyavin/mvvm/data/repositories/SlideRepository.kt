package com.molyavin.mvvm.data.repositories

import com.molyavin.mvvm.data.model.SlideDTO

interface SlideRepository {
    suspend fun getSlides():List<SlideDTO>
}