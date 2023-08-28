package com.molyavin.mvvm.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.data.repositories.SlideRepository
import com.molyavin.mvvm.domain.models.Slide
import com.molyavin.mvvm.presentation.controllers.AuthorizationController
import javax.inject.Inject

class OnBoardingViewModel @Inject constructor(
    router: Router,
    private val slideRepository: SlideRepository,
) : BaseViewModel(router = router) {

    private val _currentSliderPosition = mutableStateOf(0)
    val currentSliderPosition: State<Int> = _currentSliderPosition

    private val _slides = mutableStateOf(emptyList<Slide>())
    val slides: State<List<Slide>> = _slides

    override fun onAttach() {
        super.onAttach()
        _slides.value = slideRepository.getSlides()
    }

    fun nextSlide() {
        if (_currentSliderPosition.value == 3) {
            startScreen(AuthorizationController())
            return
        }
        _currentSliderPosition.value += 1
    }

    fun startAuthController() {
        startScreen(AuthorizationController())
    }

}