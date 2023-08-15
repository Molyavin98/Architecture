package com.molyavin.mvvm.presentation.screens.profile.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import com.bluelinelabs.conductor.Controller
import com.molyavin.mvvm.domain.di.component.Injector
import com.molyavin.mvvm.presentation.DefaultText
import com.molyavin.mvvm.presentation.screens.profile.viewmodel.ProfileViewModel
import com.molyavin.mvvm.presentation.ui.theme.MVVMTheme
import javax.inject.Inject

class ProfileController : Controller() {

    @Inject
    lateinit var viewModel: ProfileViewModel

    @SuppressLint("SetTextI18n", "NewApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {

        Injector.INSTANCE.inject(this)

        val view = ComposeView(container.context).apply {
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
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        DefaultText(text = "Full name: ${viewModel.userInfoLiveData.value?.fullName}")
                        DefaultText(text = "Phone: ${viewModel.userInfoLiveData.value?.phone}")
                        DefaultText(text = "Password: ${viewModel.userInfoLiveData.value?.password}")

                    }
                }
            }
        }

        viewModel.onCreate()

        return view
    }
}