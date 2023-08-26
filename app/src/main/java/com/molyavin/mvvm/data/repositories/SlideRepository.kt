package com.molyavin.mvvm.data.repositories

import com.molyavin.mvvm.domain.models.Slide

interface SlideRepository {

    fun getSlides():List<Slide>
}