package com.molyavin.mvvm.presentation.controllers.main

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.molyavin.mvvm.di.Injector
import com.molyavin.mvvm.presentation.controllers.BaseViewController
import com.molyavin.mvvm.presentation.ui.DefaultCenterAlignedTopAppBar
import com.molyavin.mvvm.presentation.viewmodels.main.MenuViewModel

class MenuController : BaseViewController() {

    override val viewModel: MenuViewModel = Injector.INSTANCE.provideMenuViewModel()

    override fun onContextAvailable(context: Context) {
        viewModel.attachRoot(this)
    }

    @Composable
    override fun content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {

            DefaultCenterAlignedTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                textTitle = "Menu",
                titleContentColor = Color.Black,
                textStyleTitle = MaterialTheme.typography.h2,
                navigationOnClick = viewModel::routeToUserProfile,
                navigationIcon = Icons.Filled.AccountCircle,
                navigationIconTint = Color.Black,
                containerColor = Color.White
            )

        }
    }
}