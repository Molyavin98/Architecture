package com.molyavin.mvvm.presentation.viewmodels.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.domain.usecase.onboarding.GetStatusOnBoardingUseCase
import com.molyavin.mvvm.domain.usecase.auth.GetStatusRememberMeUseCase
import com.molyavin.mvvm.domain.usecase.splash.StartScreenUseCase
import com.molyavin.mvvm.presentation.controllers.auth.AuthorizationController
import com.molyavin.mvvm.presentation.controllers.main.MenuController
import com.molyavin.mvvm.presentation.controllers.onboarding.OnBoardingController
import com.molyavin.mvvm.presentation.viewmodels.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val startScreenUseCase: StartScreenUseCase,
    val router: Router
) : BaseViewModel(router = router, toaster = null) {

    override fun onCreateView() {
        findNextRouterNode()
    }

    private fun findNextRouterNode() {
        viewModelScope.launch {
            startCoroutine(runnable = {
                val node = startScreenUseCase.execute(null)
                startScreen(node.controllerClass.newInstance())
            })
        }
    }

}