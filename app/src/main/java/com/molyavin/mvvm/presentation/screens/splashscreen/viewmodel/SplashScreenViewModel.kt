package com.molyavin.mvvm.presentation.screens.splashscreen.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.domain.usecase.GetStatusRememberMeUseCase
import com.molyavin.mvvm.domain.usecase.GetStatusOnBoardingUseCase
import com.molyavin.mvvm.presentation.screens.authorization.screen.AuthorizationController
import com.molyavin.mvvm.presentation.screens.menu.screen.MenuController
import com.molyavin.mvvm.presentation.screens.onboarding.screen.OnBoardingController
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val getStatusOnBoardingUseCase: GetStatusOnBoardingUseCase,
    private val getStatusRememberMeUseCase: GetStatusRememberMeUseCase,
    val router: Router
) : ViewModel() {

    private val _statusRememberMe = mutableStateOf("")
    private val _statusOnBoarding = mutableStateOf("")
    fun checkStatus() {
        _statusOnBoarding.value = getStatusOnBoardingUseCase.execute(null)
        _statusRememberMe.value = getStatusRememberMeUseCase.execute(null)
    }

    fun startScreen() {

        when (_statusOnBoarding.value) {

            "On" -> router.pushController(RouterTransaction.with(OnBoardingController()))

            "Off" -> {
                if (_statusRememberMe.value == "On") {
                    router.pushController(RouterTransaction.with(MenuController()))
                } else if (_statusRememberMe.value == "Off") {
                    router.pushController(RouterTransaction.with(AuthorizationController()))
                }
            }

        }
    }

}