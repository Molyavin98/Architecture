package com.molyavin.mvvm.domain.models

import com.molyavin.mvvm.data.model.ProductDTO


data class ProductVM(
    val id: Int,
    val brand: String,
    val title: String,
    val description: String,
    val price: Int,
    val images: List<String>,
) {
    companion object {

        fun empty() = ProductVM(0, "", "", "", 0, arrayListOf(""))
    }
}

fun ProductDTO.toVM(): ProductVM {
    return ProductVM(
        id = id,
        brand = brand,
        title = title,
        description = description,
        price = price,
        images = images
    )
}

