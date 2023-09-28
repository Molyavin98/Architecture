package com.molyavin.mvvm.data.repositories

import com.molyavin.mvvm.data.model.InfoDTO

interface InfoAppRepository {

    fun getInfo():List<InfoDTO>
}