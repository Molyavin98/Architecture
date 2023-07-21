package com.molyavin.mvvm.presenter

import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.domain.repository.UserRepository
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase

class AuthorizationPresenter(var view: MainContract.View?, var userRepository: UserRepository) :
    MainContract.Authorization {

    private var phoneUser: String = ""
    private var passwordUser: String = ""

    private lateinit var readUserInfoUseCase: ReadUserInfoUseCase
    private lateinit var userInfo: UserInfo

    override fun setUserData(phoneNumber: String, password: String) {
        phoneUser = phoneNumber
        passwordUser = password
    }

    override fun validateUserData(): Boolean {

        if (phoneUser == userInfo.phone && passwordUser == userInfo.password) {
            return true
        }
        view?.setErrorUserData()
        return false
    }
    fun readDataUser() {
        readUserInfoUseCase = ReadUserInfoUseCase(userRepository)
        userInfo = readUserInfoUseCase.execute()

    }

    override fun onDestroy() {
        view = null
    }

}