package com.molyavin.mvvm.presentation.screens.login.presenter

import androidx.lifecycle.ViewModel
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.controlles.MenuController
import com.molyavin.mvvm.controlles.RegistrationController
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase
import com.molyavin.mvvm.utils.Toaster
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val readUserInfoUseCase: ReadUserInfoUseCase,
    private val router: Router,
    private val toaster: Toaster,
) : ViewModel() {
    fun login(phoneUser: String, passwordUser: String) {


        if (phoneUser == readUserInfoUseCase.execute(null).phone &&
            passwordUser == readUserInfoUseCase.execute(null).password
        ) {
            router.setRoot(RouterTransaction.with(MenuController()))

        } else if (phoneUser.isEmpty() || passwordUser.isEmpty()) {
            toaster.show("Field is not can empty!")
        } else {
            toaster.show("Data user is not correct!")
        }
    }

    fun goToRegistrationScreen() {
        router.pushController(RouterTransaction.with(RegistrationController()))
    }

}