package com.molyavin.mvvm.data.model

interface DataStoreItemDTO {

    fun copyDTO(parameters: Map<String, String>): DataStoreItemDTO

    fun getItemInfo(): Map<String, String>

}