package com.molyavin.mvvm.presentation.controllers

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import com.molyavin.mvvm.domain.di.component.Injector
import com.molyavin.mvvm.presentation.viewmodels.MenuViewModel
import com.molyavin.mvvm.presentation.ui.theme.MVVMTheme
import javax.inject.Singleton

class MenuController : BaseViewController() {

    override lateinit var viewModel: MenuViewModel

    override fun setupView(view: ComposeView) {

        viewModel = Injector.INSTANCE.provideMenuViewModel()

        this.viewModel.attachRoot(this)

        view.setContent {
            MVVMTheme {
                Scaffold {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize(),
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
                            IconButton(onClick = { viewModel.startScreen(SettingController()) }) {
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
        }
    }

}