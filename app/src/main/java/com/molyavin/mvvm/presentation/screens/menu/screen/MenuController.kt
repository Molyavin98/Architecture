package com.molyavin.mvvm.presentation.screens.menu.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.bluelinelabs.conductor.Controller
import com.molyavin.mvvm.R
import com.molyavin.mvvm.domain.di.component.Injector
import com.molyavin.mvvm.presentation.screens.menu.viewmodel.MenuViewModel
import com.molyavin.mvvm.presentation.ui.theme.MVVMTheme
import javax.inject.Inject

class MenuController : Controller() {


    @Inject
    lateinit var viewModel: MenuViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {

        Injector.INSTANCE.inject(this)

        val view = ComposeView(context = container.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        view.setContent {
            MVVMTheme {
                Scaffold {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                    ) {

                        Row {

                            IconButton(
                                modifier = Modifier
                                    .size(50.dp)
                                    .weight(1f),
                                onClick = {viewModel.startScreenProfile()}) {

                                Image(
                                    imageVector = ImageVector.vectorResource(R.drawable.account_icon),
                                    contentDescription = null
                                )
                            }

                            IconButton(
                                modifier = Modifier
                                    .size(50.dp)
                                    .weight(1f),
                                onClick = {}) {

                                Image(
                                    imageVector = ImageVector.vectorResource(R.drawable.settings_icon),
                                    contentDescription = null
                                )
                            }

                        }
                    }
                }
            }
        }

        return view
    }

}