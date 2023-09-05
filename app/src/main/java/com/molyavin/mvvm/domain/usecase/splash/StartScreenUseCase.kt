package com.molyavin.mvvm.domain.usecase.splash

import android.util.Log
import com.molyavin.mvvm.domain.models.RouterNode
import com.molyavin.mvvm.domain.usecase.auth.GetStatusRememberMeUseCase
import com.molyavin.mvvm.domain.usecase.base.IAsyncUseCase
import com.molyavin.mvvm.domain.usecase.onboarding.GetStatusOnBoardingUseCase
import com.molyavin.mvvm.presentation.controllers.auth.AuthorizationController
import com.molyavin.mvvm.presentation.controllers.main.MenuController
import com.molyavin.mvvm.presentation.controllers.onboarding.OnBoardingController
import kotlinx.coroutines.delay
import javax.inject.Inject

class StartScreenUseCase @Inject constructor(
    private val getStatusOnBoardingUseCase: GetStatusOnBoardingUseCase,
    private val getStatusRememberMeUseCase: GetStatusRememberMeUseCase,
) : IAsyncUseCase<Any?, RouterNode<*>> {

    override suspend fun execute(income: Any?): RouterNode<*> {
        delay(2000)

        val statusOnBoarding = getStatusOnBoardingUseCase.execute(null)

        Log.d("OnBoardingStatus", statusOnBoarding.toString())
        val statusRememberMe = getStatusRememberMeUseCase.execute(null)

        Log.d("Statussss","$statusOnBoarding     $statusRememberMe")

        return when {
            !statusOnBoarding && !statusRememberMe -> RouterNode(AuthorizationController::class.java)
            !statusOnBoarding && statusRememberMe -> RouterNode(MenuController::class.java)
            else -> RouterNode(OnBoardingController::class.java)
        }
    }
}
