package com.molyavin.mvvm.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.data.repositories.SlideRepository
import com.molyavin.mvvm.domain.models.Slide
import javax.inject.Inject

class OnBoardingViewModel @Inject constructor(
    router: Router,
    private val slideRepository: SlideRepository
) :
    BaseViewModel(router = router) {

    private var _slideCount = mutableStateOf(0)
    val slideCount: State<Int> = _slideCount
    fun getSlides(): List<Slide> = slideRepository.getSlides()

    fun nextSlide(slideCount: Int){
        _slideCount.value = slideCount
    }

}