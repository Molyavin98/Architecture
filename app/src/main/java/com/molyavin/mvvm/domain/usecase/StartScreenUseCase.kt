package com.molyavin.mvvm.domain.usecase

import android.os.Handler
import com.molyavin.mvvm.presentation.screens.splashscreen.viewmodel.SplashScreenViewModel
import javax.inject.Inject

class StartScreenUseCase @Inject constructor(private val handler: Handler) :
    IUseCase.IStartScreenUseCase {

    override fun execute(income: SplashScreenViewModel) {
        handler.postDelayed({
            income.checkStatuses()
        }, 3000)
    }
}
