package com.molyavin.mvvm.data.repositories

interface WordRepository {

    fun saveId(id:String)

    fun getID():Int

}