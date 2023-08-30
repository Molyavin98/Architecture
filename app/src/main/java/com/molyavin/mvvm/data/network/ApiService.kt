package com.molyavin.mvvm.data.network

import com.molyavin.mvvm.R
import com.molyavin.mvvm.data.model.SlideDTO
import kotlinx.coroutines.delay

class ApiService {
    suspend fun fetchSlides(): List<SlideDTO> {
        delay(2000)

        return listOf(
            SlideDTO(
                idImage = R.drawable.jetpack_compose_icon,
                title = "JetPack Compose",
                description = "Jetpack Compose is Android's recommended modern toolkit for building native UI"
            ),
            SlideDTO(
                idImage = R.drawable.android_icone,
                title = "Android",
                description = "Android is a mobile operating system based on a modified version of the Linux kernel"
            ),
            SlideDTO(
                idImage = R.drawable.architecture_image,
                title = "Architecture App",
                description = "In the past computers needed to be disconnected from their internal network if they needed to be taken or moved anywhere"
            ),
            SlideDTO(
                idImage = R.drawable.bitcoin_image,
                title = "Bitcoin",
                description = "Bitcoin is a protocol which implements a public, permanent, and decentralized ledger"
            ),
        )
    }
}