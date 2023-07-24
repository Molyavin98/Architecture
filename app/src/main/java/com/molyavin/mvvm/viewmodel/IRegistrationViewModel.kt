package com.molyavin.mvvm.viewmodel

interface IRegistrationViewModel {

    fun setUserData(userFullName: String, userPhone: String, userPassword: String)
    fun checkField(fullName: String, phone: String, password: String): Boolean
    fun saveData()
}