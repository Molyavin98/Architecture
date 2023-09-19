package com.molyavin.mvvm.domain.models

import com.molyavin.mvvm.data.model.WordDTO

data class WordVM(
    val id: String? = null,
    val eng: String?,
    val ua: String?,
) {
    companion object {
        fun empty() = WordVM("", "", "")
    }

}

fun WordDTO.toVM(): WordVM {
    return WordVM(
        id = id,
        eng = eng,
        ua = ua,
    )
}

