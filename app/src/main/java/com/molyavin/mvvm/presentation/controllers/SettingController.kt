package com.molyavin.mvvm.presentation.controllers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import com.molyavin.mvvm.domain.di.component.Injector
import com.molyavin.mvvm.presentation.viewmodels.SettingViewModel
import com.molyavin.mvvm.presentation.ui.theme.MVVMTheme

class SettingController : BaseViewController() {

    override lateinit var viewModel: SettingViewModel

    @OptIn(ExperimentalMaterial3Api::class)
    override fun setupView(view: ComposeView) {

        viewModel = Injector.INSTANCE.provideSettingViewModel()

        view.setContent {

            MVVMTheme {
                Scaffold {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                    }

                }
            }


        }
    }

}