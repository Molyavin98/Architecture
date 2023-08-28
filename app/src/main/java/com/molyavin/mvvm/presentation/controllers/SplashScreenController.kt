package com.molyavin.mvvm.presentation.controllers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import com.molyavin.mvvm.R
import com.molyavin.mvvm.domain.di.component.Injector
import com.molyavin.mvvm.presentation.DefaultImageLogo
import com.molyavin.mvvm.presentation.DefaultText
import com.molyavin.mvvm.presentation.viewmodels.SplashScreenViewModel
import com.molyavin.mvvm.presentation.ui.theme.MVVMTheme
import javax.inject.Singleton

class SplashScreenController : BaseViewController() {


    override lateinit var viewModel: SplashScreenViewModel
    override fun setupView(view: ComposeView) {

        viewModel = Injector.INSTANCE.provideSplashScreenViewModel()

        view.setContent {
            MVVMTheme {
                androidx.compose.material.Scaffold {

                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        DefaultImageLogo(idImage = R.drawable.jetpack_icon)
                        DefaultText(text = "Architecture App")
                    }
                }
            }
        }
        viewModel.startScreen()
    }
}

