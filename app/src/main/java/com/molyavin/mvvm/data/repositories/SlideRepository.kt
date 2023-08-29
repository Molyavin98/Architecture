package com.molyavin.mvvm.data.repositories

import com.molyavin.mvvm.domain.models.SlideEntity
import com.molyavin.mvvm.domain.models.SlideVM

interface SlideRepository {
    suspend fun getSlides():List<SlideEntity>
}