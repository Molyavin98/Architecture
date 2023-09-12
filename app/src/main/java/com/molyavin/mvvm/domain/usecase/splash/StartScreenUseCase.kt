package com.molyavin.mvvm.domain.usecase.splash

import com.molyavin.mvvm.domain.models.RouterNode
import com.molyavin.mvvm.domain.usecase.auth.GetStatusRememberMeUseCase
import com.molyavin.mvvm.domain.usecase.base.IUseCase
import com.molyavin.mvvm.domain.usecase.onboarding.GetStatusOnBoardingUseCase
import com.molyavin.mvvm.presentation.controllers.auth.AuthorizationController
import com.molyavin.mvvm.presentation.controllers.main.MenuController
import com.molyavin.mvvm.presentation.controllers.onboarding.OnBoardingController
import io.reactivex.Single
import javax.inject.Inject

class StartScreenUseCase @Inject constructor(
    private val getStatusOnBoardingUseCase: GetStatusOnBoardingUseCase,
    private val getStatusRememberMeUseCase: GetStatusRememberMeUseCase,
) : IUseCase<Any?, Single<RouterNode<*>>> {

    override fun execute(income: Any?): Single<RouterNode<*>> {
        return Single.zip(
            getStatusOnBoardingUseCase.execute(null),
            getStatusRememberMeUseCase.execute(null),
        ) { statusOnBoarding, statusRememberMe ->
            when {
                !statusOnBoarding && !statusRememberMe -> RouterNode(AuthorizationController::class.java)
                !statusOnBoarding && statusRememberMe -> RouterNode(MenuController::class.java)
                else -> RouterNode(OnBoardingController::class.java)
            }
        }
    }
}
