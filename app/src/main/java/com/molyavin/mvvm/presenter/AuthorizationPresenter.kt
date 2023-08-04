package com.molyavin.mvvm.presenter

import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase
import com.molyavin.mvvm.presentation.View
import javax.inject.Inject

class AuthorizationPresenter @Inject constructor (
    private var readUserInfoUseCase: ReadUserInfoUseCase
) : Presenter.Authorization {

    private var view: View.AuthorizationViewView? = null
    private lateinit var userInfo: UserInfo

    override fun onAttach(view: View.AuthorizationViewView) {
        this.view = view
    }

    override fun validateUserData(phoneUser: String, passwordUser: String): Boolean {
        if (phoneUser == userInfo.phone && passwordUser == userInfo.password) {
            return true
        }
        view?.setErrorUserData()
        return false
    }

    fun readDataUser() {
        userInfo = readUserInfoUseCase.execute(null)
    }

    override fun onDestroy() {
        view = null
    }

}