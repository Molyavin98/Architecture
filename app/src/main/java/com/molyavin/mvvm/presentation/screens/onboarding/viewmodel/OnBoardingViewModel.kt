package com.molyavin.mvvm.presentation.screens.onboarding.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.R
import com.molyavin.mvvm.domain.models.PagerContent
import com.molyavin.mvvm.presentation.BaseViewModel
import com.molyavin.mvvm.utils.Toaster
import javax.inject.Inject

class OnBoardingViewModel @Inject constructor(router: Router) :
    BaseViewModel(router = router, toaster = null) {

    var pageCount by mutableStateOf(0)

    fun createItems() = listOf(
        PagerContent(
            idImage = R.drawable.jetpack_compose_icon,
            title = "JetPack Compose",
            description = "Jetpack Compose is Android's recommended modern toolkit for building native UI"
        ),
        PagerContent(
            idImage = R.drawable.android_icone,
            title = "Android",
            description = "Android is a mobile operating system based on a modified version of the Linux kernel"
        ),
        PagerContent(
            idImage = R.drawable.architecture_image,
            title = "Architecture App",
            description = "In the past computers needed to be disconnected from their internal network if they needed to be taken or moved anywhere"
        ),
        PagerContent(
            idImage = R.drawable.bitcoin_image,
            title = "Bitcoin",
            description = "Bitcoin is a protocol which implements a public, permanent, and decentralized ledger"
        ),
    )

}