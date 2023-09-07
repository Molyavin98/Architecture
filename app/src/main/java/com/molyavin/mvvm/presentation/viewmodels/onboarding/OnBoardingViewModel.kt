package com.molyavin.mvvm.presentation.viewmodels.onboarding

import androidx.lifecycle.viewModelScope
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.data.model.SlideDTO
import com.molyavin.mvvm.domain.models.RouterNode
import com.molyavin.mvvm.domain.usecase.onboarding.GetSlideAsyncUseCase
import com.molyavin.mvvm.presentation.controllers.auth.AuthorizationController
import com.molyavin.mvvm.presentation.viewmodels.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnBoardingViewModel @Inject constructor(
    router: Router,
    private val getSlideUseCase: GetSlideAsyncUseCase,
) : BaseViewModel(router = router) {

    private val _currentSliderPosition = MutableStateFlow(0)
    val currentSliderPosition: StateFlow<Int> = _currentSliderPosition

    private val _slides = MutableStateFlow(emptyList<SlideDTO>())
    val slides: StateFlow<List<SlideDTO>> = _slides

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
            nextScreen(RouterNode(AuthorizationController::class.java))
            return
        }
        _currentSliderPosition.value += 1
    }

    fun startAuthController() {
        nextScreen(RouterNode(AuthorizationController::class.java))
    }

}