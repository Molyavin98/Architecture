package com.molyavin.mvvm.data.storage

import com.molyavin.mvvm.R
import com.molyavin.mvvm.domain.models.SlideEntity
import kotlinx.coroutines.delay

class ApiService {
    suspend fun fetchSlides(): List<SlideEntity> {
        delay(5000)

        return listOf(
            SlideEntity(
                idImage = R.drawable.jetpack_compose_icon,
                title = "JetPack Compose",
                description = "Jetpack Compose is Android's recommended modern toolkit for building native UI"
            ),
            SlideEntity(
                idImage = R.drawable.android_icone,
                title = "Android",
                description = "Android is a mobile operating system based on a modified version of the Linux kernel"
            ),
            SlideEntity(
                idImage = R.drawable.architecture_image,
                title = "Architecture App",
                description = "In the past computers needed to be disconnected from their internal network if they needed to be taken or moved anywhere"
            ),
            SlideEntity(
                idImage = R.drawable.bitcoin_image,
                title = "Bitcoin",
                description = "Bitcoin is a protocol which implements a public, permanent, and decentralized ledger"
            ),
        )
    }
}