package com.molyavin.mvvm.data.repositories

import com.molyavin.mvvm.data.model.InfoDTO
import com.molyavin.mvvm.data.network.ApiServiceInfoApp
import javax.inject.Inject

class InfoAppRepositoryImpl @Inject constructor(private val apiServiceInfoApp: ApiServiceInfoApp) :
    InfoAppRepository {

    override fun getInfo(): List<InfoDTO> {
        return apiServiceInfoApp.fetchInfo()
    }
}