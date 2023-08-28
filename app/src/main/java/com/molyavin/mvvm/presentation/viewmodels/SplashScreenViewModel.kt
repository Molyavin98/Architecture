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

    private val _statusRememberMe = mutableStateOf("")
    private val _statusOnBoarding = mutableStateOf("")
    fun checkStatuses() {

        _statusOnBoarding.value = getStatusOnBoardingUseCase.execute(null)
        _statusRememberMe.value = getStatusRememberMeUseCase.execute(null)

        when {

            _statusOnBoarding.value == "On" && _statusRememberMe.value == "Off" ->
                startScreen(OnBoardingController())

            _statusOnBoarding.value == "Off" && _statusRememberMe.value == "Off" ->
                startScreen(AuthorizationController())

            _statusOnBoarding.value == "Off" && _statusRememberMe.value == "On" ->
                startScreen(MenuController())

        }
    }
    fun startScreen() {
        startScreenUseCase.execute(this)
    }

}