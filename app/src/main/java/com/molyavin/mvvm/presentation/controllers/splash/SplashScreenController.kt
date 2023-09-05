package com.molyavin.mvvm.presentation.controllers.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.molyavin.mvvm.R
import com.molyavin.mvvm.di.Injector
import com.molyavin.mvvm.presentation.controllers.BaseViewController
import com.molyavin.mvvm.presentation.ui.DefaultImageLogo
import com.molyavin.mvvm.presentation.ui.DefaultText
import com.molyavin.mvvm.presentation.viewmodels.splash.SplashScreenViewModel

class SplashScreenController : BaseViewController() {

    override val viewModel: SplashScreenViewModel = Injector.INSTANCE.provideSplashScreenViewModel()

    @Composable
    override fun content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            DefaultImageLogo(idImage = R.drawable.jetpack_icon)
            DefaultText(text = "Architecture App")
        }
    }
}

