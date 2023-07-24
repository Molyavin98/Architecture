package com.molyavin.mvvm.presenter

import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase

class AuthorizationPresenter(
    var view: MainContract.View?,
    private var readUserInfoUseCase: ReadUserInfoUseCase
) : MainContract.Authorization {

    private lateinit var userInfo: UserInfo

    override fun validateUserData(phoneUser: String, passwordUser: String): Boolean {

        if (phoneUser == userInfo.phone && passwordUser == userInfo.password) {
            return true
        }
        view?.setErrorUserData()
        return false
    }

    fun readDataUser() {
        userInfo = readUserInfoUseCase.execute()
    }

    override fun onDestroy() {
        view = null
    }

}