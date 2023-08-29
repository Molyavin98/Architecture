package com.molyavin.mvvm.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.domain.models.SlideVM
import com.molyavin.mvvm.domain.usecase.GetSlideAsyncUseCase
import com.molyavin.mvvm.presentation.controllers.AuthorizationController
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnBoardingViewModel @Inject constructor(
    router: Router,
    private val getSlideUseCase: GetSlideAsyncUseCase,
) : BaseViewModel(router = router) {

    private val _currentSliderPosition = mutableStateOf(0)
    val currentSliderPosition: State<Int> = _currentSliderPosition

    private val _slides = mutableStateOf(emptyList<SlideVM>())
    val slides: State<List<SlideVM>> = _slides

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

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