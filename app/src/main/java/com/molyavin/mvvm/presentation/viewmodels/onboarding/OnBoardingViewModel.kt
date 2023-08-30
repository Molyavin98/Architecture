package com.molyavin.mvvm.presentation.viewmodels.onboarding

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.data.model.SlideDTO
import com.molyavin.mvvm.domain.usecase.onboarding.GetSlideAsyncUseCase
import com.molyavin.mvvm.presentation.controllers.auth.AuthorizationController
import com.molyavin.mvvm.presentation.viewmodels.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnBoardingViewModel @Inject constructor(
    router: Router,
    private val getSlideUseCase: GetSlideAsyncUseCase,
) : BaseViewModel(router = router) {

    private val _currentSliderPosition = mutableStateOf(0)
    val currentSliderPosition: State<Int> = _currentSliderPosition

    private val _slides = mutableStateOf(emptyList<SlideDTO>())
    val slides: State<List<SlideDTO>> = _slides

    override fun onAttach() {
        super.onAttach()
        loadingSlide()
    }

    private fun loadingSlide() {
        viewModelScope.launch {
            _isLoading.value = true
            _slides.value = getSlideUseCase.execute(null)
            _isLoading.value = false
        }

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