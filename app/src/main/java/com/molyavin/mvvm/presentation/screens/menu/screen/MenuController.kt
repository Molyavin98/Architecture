package com.molyavin.mvvm.presentation.screens.menu.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.bluelinelabs.conductor.Controller
import com.molyavin.mvvm.domain.di.component.Injector
import com.molyavin.mvvm.presentation.BaseViewController
import com.molyavin.mvvm.presentation.screens.menu.viewmodel.MenuViewModel
import com.molyavin.mvvm.presentation.screens.profile.screen.ProfileController
import com.molyavin.mvvm.presentation.ui.theme.MVVMTheme
import javax.inject.Inject

class MenuController : BaseViewController() {

    @Inject
    lateinit var viewModel: MenuViewModel
    override fun setupView(view: ComposeView) {

        Injector.INSTANCE.inject(this)

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
                            IconButton(onClick = { viewModel.startScreen(ProfileController()) }) {
                                Icon(
                                    imageVector = Icons.Filled.AccountCircle,
                                    tint = Color.White,
                                    contentDescription = null
                                )
                            }
                            Spacer(Modifier.weight(1f, true))
                            IconButton(onClick = { }) {
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