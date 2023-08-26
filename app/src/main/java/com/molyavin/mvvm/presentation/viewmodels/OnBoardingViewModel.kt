package com.molyavin.mvvm.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.data.repositories.SlideRepository
import com.molyavin.mvvm.domain.models.Slide
import javax.inject.Inject

class OnBoardingViewModel @Inject constructor(
    router: Router,
    private val slideRepository: SlideRepository
) :
    BaseViewModel(router = router) {

    var slideCount by mutableStateOf(0)
    fun getSlides(): List<Slide> = slideRepository.getSlides()

}