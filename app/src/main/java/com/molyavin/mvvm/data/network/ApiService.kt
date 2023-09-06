package com.molyavin.mvvm.data.network

import com.molyavin.mvvm.data.model.SlideDTO


class ApiService {

    fun fetchSlides(): List<SlideDTO> {

        return listOf(
            SlideDTO(
                urlImage = "https://3.bp.blogspot.com/-VVp3WvJvl84/X0Vu6EjYqDI/AAAAAAAAPjU/ZOMKiUlgfg8ok8DY8Hc-ocOvGdB0z86AgCLcBGAsYHQ/s1600/jetpack%2Bcompose%2Bicon_RGB.png",
                title = "JetPack Compose",
                description = "Jetpack Compose is Android's recommended modern toolkit for building native UI"
            ),
            SlideDTO(
                urlImage = "https://upload.wikimedia.org/wikipedia/commons/3/3e/Android_logo_2019.png",
                title = "Android",
                description = "Android is a mobile operating system based on a modified version of the Linux kernel"
            ),
            SlideDTO(
                urlImage = "https://www.seekpng.com/png/full/831--or--construction-clip-art.png",
                title = "Architecture App",
                description = "In the past computers needed to be disconnected from their internal network if they needed to be taken or moved anywhere"
            ),
            SlideDTO(
                urlImage = "https://freepngimg.com/thumb/bitcoin/59783-cryptocurrency-money-blockchain-bitcoin-cash-free-transparent-image-hq.png",
                title = "Bitcoin",
                description = "Bitcoin is a protocol which implements a public, permanent, and decentralized ledger"
            ),
        )
    }
}