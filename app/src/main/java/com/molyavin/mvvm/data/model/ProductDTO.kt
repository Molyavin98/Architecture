package com.molyavin.mvvm.data.model

data class ProductDTO(
    val id: Int,
    val brand: String,
    val title: String,
    val description: String,
    val price: Int,
    val images: List<String>,
)