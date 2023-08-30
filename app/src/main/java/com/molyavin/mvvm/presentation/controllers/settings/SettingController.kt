package com.molyavin.mvvm.presentation.controllers.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import com.molyavin.mvvm.di.Injector
import com.molyavin.mvvm.presentation.controllers.BaseViewController
import com.molyavin.mvvm.presentation.viewmodels.settings.SettingViewModel
import com.molyavin.mvvm.presentation.ui.theme.MVVMTheme

class SettingController : BaseViewController() {

    override val viewModel: SettingViewModel = Injector.INSTANCE.provideSettingViewModel()

    @Composable
    override fun content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

        }
    }

}