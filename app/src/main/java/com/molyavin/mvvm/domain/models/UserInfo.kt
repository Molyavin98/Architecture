package com.molyavin.mvvm.domain.models

data class UserInfo(val phone: String, val passwordOne: String, val passwordTwo: String){

    companion object{
        fun empty(): UserInfo = UserInfo("","","")
    }
}