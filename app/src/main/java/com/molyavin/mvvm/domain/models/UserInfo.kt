package com.molyavin.mvvm.domain.models

data class UserInfo(val fullName: String, val phone: String, val password: String){

    companion object{
        fun empty(): UserInfo = UserInfo("","","")
    }
}