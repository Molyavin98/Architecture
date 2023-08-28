package com.molyavin.mvvm.data.repositories

import com.molyavin.mvvm.R
import com.molyavin.mvvm.domain.models.Slide

class SlideRepositoryImpl : SlideRepository {

    override fun getSlides(): List<Slide> = listOf(
        Slide(
            idImage = R.drawable.jetpack_compose_icon,
            title = "JetPack Compose",
            description = "Jetpack Compose is Android's recommended modern toolkit for building native UI"
        ),
        Slide(
            idImage = R.drawable.android_icone,
            title = "Android",
            description = "Android is a mobile operating system based on a modified version of the Linux kernel"
        ),
        Slide(
            idImage = R.drawable.architecture_image,
            title = "Architecture App",
            description = "In the past computers needed to be disconnected from their internal network if they needed to be taken or moved anywhere"
        ),
        Slide(
            idImage = R.drawable.bitcoin_image,
            title = "Bitcoin",
            description = "Bitcoin is a protocol which implements a public, permanent, and decentralized ledger"
        ),
    )
}