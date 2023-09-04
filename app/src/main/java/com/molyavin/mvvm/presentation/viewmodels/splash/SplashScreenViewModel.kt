package com.molyavin.mvvm.presentation.viewmodels.splash

import androidx.lifecycle.viewModelScope
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.domain.usecase.splash.StartScreenUseCase
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
            startCoroutine(runnable = { nextScreen(startScreenUseCase.execute(null)) })
        }
    }

}