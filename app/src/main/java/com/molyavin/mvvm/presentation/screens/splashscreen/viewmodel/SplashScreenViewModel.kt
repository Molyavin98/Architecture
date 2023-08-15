package com.molyavin.mvvm.presentation.screens.splashscreen.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.domain.usecase.ReadStatusScreenUseCase
import com.molyavin.mvvm.presentation.screens.authorization.screen.AuthorizationController
import com.molyavin.mvvm.presentation.screens.onboarding.screen.OnBoardingController
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val readStatusScreenUseCase: ReadStatusScreenUseCase,
    val router: Router
) : ViewModel() {

    private val _checkStatus = MutableLiveData<String>()
    val checkStatus: LiveData<String> = _checkStatus

    fun checkStatusScreen() {
        _checkStatus.value = readStatusScreenUseCase.execute(null)
    }

    fun startScreen() {

        if (checkStatus.value.equals("On")) {
            router.setRoot(RouterTransaction.with(OnBoardingController()))
            router.pushController(RouterTransaction.with(OnBoardingController()))
        } else if (checkStatus.value.equals("Off")) {
            router.setRoot(RouterTransaction.with(AuthorizationController()))
            router.pushController(RouterTransaction.with(AuthorizationController()))
        }


    }


}