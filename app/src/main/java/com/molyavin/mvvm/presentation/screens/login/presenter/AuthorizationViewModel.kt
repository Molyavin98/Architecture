package com.molyavin.mvvm.presentation.screens.login.presenter

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.controlles.MenuController
import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase
import com.molyavin.mvvm.utils.Toaster
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val readUserInfoUseCase: ReadUserInfoUseCase,
    private val router: Router,
    private val toaster: Toaster,
) : ViewModel() {

    private lateinit var userInfo: UserInfo

    fun login(phoneUser: String, passwordUser: String) {
        if (phoneUser == userInfo.phone && passwordUser == userInfo.password) {
            router.setRoot(RouterTransaction.with(MenuController()))
        } else if (phoneUser.isEmpty() || passwordUser.isEmpty()) {
            toaster.show("Field is not can empty!")
        } else {
            toaster.show("Data user is not correct!")
        }
    }

    fun readDataUser() {
        userInfo = readUserInfoUseCase.execute(null)
    }

}