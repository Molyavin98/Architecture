package com.molyavin.mvvm.presentation.screens.splashscreen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.domain.usecase.ReadStatusAuntUseCase
import com.molyavin.mvvm.domain.usecase.ReadStatusScreenUseCase
import com.molyavin.mvvm.presentation.screens.authorization.screen.AuthorizationController
import com.molyavin.mvvm.presentation.screens.menu.screen.MenuController
import com.molyavin.mvvm.presentation.screens.onboarding.screen.OnBoardingController
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val readStatusScreenUseCase: ReadStatusScreenUseCase,
    private val readStatusAuntUseCase: ReadStatusAuntUseCase,
    val router: Router
) : ViewModel() {

    private val _checkStatus = MutableLiveData<String>()
    private val _checkStatusAunt = MutableLiveData<String>()

    fun checkStatus() {
        _checkStatus.value = readStatusScreenUseCase.execute(null)
        _checkStatusAunt.value = readStatusAuntUseCase.execute(null)
    }
    fun startScreen() {

        if (_checkStatus.value.equals("On")) {
            router.setRoot(RouterTransaction.with(OnBoardingController()))
            router.pushController(RouterTransaction.with(OnBoardingController()))
        } else if (_checkStatus.value.equals("Off")) {

            if (_checkStatusAunt.value.equals("On")) {
                router.setRoot(RouterTransaction.with(MenuController()))
                router.pushController(RouterTransaction.with(MenuController()))
            } else if (_checkStatusAunt.value.equals("Off")) {
                router.setRoot(RouterTransaction.with(AuthorizationController()))
                router.pushController(RouterTransaction.with(AuthorizationController()))
            }
        }
    }


}