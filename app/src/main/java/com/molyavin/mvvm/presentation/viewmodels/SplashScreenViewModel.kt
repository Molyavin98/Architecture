package com.molyavin.mvvm.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.domain.usecase.GetStatusOnBoardingUseCase
import com.molyavin.mvvm.domain.usecase.GetStatusRememberMeUseCase
import com.molyavin.mvvm.domain.usecase.StartScreenUseCase
import com.molyavin.mvvm.presentation.controllers.AuthorizationController
import com.molyavin.mvvm.presentation.controllers.MenuController
import com.molyavin.mvvm.presentation.controllers.OnBoardingController
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val getStatusOnBoardingUseCase: GetStatusOnBoardingUseCase,
    private val getStatusRememberMeUseCase: GetStatusRememberMeUseCase,
    private val startScreenUseCase: StartScreenUseCase,
    val router: Router
) : BaseViewModel(router = router, toaster = null) {

    private val _statusRememberMe = mutableStateOf(false)
    private val _statusOnBoarding = mutableStateOf(false)
    fun checkStatuses() {

        _statusOnBoarding.value = getStatusOnBoardingUseCase.execute(null)
        _statusRememberMe.value = getStatusRememberMeUseCase.execute(null)

        when {

            _statusOnBoarding.value -> startScreen(OnBoardingController())

            !_statusOnBoarding.value && !_statusRememberMe.value ->
                startScreen(AuthorizationController())

            !_statusOnBoarding.value && _statusRememberMe.value ->
                startScreen(MenuController())

        }
    }

    fun startScreen() {
        startScreenUseCase.execute(this)
    }

}