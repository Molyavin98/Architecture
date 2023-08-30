package com.molyavin.mvvm.presentation.controllers.main

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import com.molyavin.mvvm.di.Injector
import com.molyavin.mvvm.presentation.controllers.BaseViewController
import com.molyavin.mvvm.presentation.controllers.profile.ProfileController
import com.molyavin.mvvm.presentation.viewmodels.main.MenuViewModel
import com.molyavin.mvvm.presentation.ui.theme.MVVMTheme

class MenuController : BaseViewController() {

    override val viewModel: MenuViewModel = Injector.INSTANCE.provideMenuViewModel()

    override fun onContextAvailable(context: Context) {
        viewModel.attachRoot(this)
    }

    @Composable
    override fun content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
        ) {

            TopAppBar(
                modifier = Modifier
            ) {
                IconButton(onClick = {
                    viewModel.startScreen(
                        ProfileController()
                    )
                }) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
                Spacer(Modifier.weight(1f, true))
                IconButton(onClick = { viewModel.startSettingController() }) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
        }
    }

}