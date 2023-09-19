package com.molyavin.mvvm.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WordDTO(
    @Json(name = "id") val id: String? = null,
    @Json(name = "eng") val eng: String?,
    @Json(name = "ua") val ua: String?,
)
