package com.molyavin.mvvm.presenter

import com.molyavin.mvvm.MainContract

class AuthorizationPresenter (var view: MainContract.View?, private val model: MainContract.Model) :
    MainContract.Authorization {

    private var phoneUser: String = ""
    private var passwordUser: String = ""

    override fun setUserData(phoneNumber: String, password: String) {
        phoneUser = phoneNumber
        passwordUser = password
    }

    override fun validateUserData(): Boolean {

        if (phoneUser == model.getData("phone") && passwordUser == model.getData("password")) {
            return true
        }
        view?.setErrorUserData()
        return false
    }

    override fun onDestroy() {
        view = null
    }

}