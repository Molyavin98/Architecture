package com.molyavin.mvvm.presentation.screens.authorization.presenter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase
import com.molyavin.mvvm.domain.usecase.SaveStatusScreenUseCase
import com.molyavin.mvvm.presentation.screens.menu.screen.MenuController
import com.molyavin.mvvm.presentation.screens.registration.screen.RegistrationController
import com.molyavin.mvvm.utils.Toaster
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val readUserInfoUseCase: ReadUserInfoUseCase,
    private val saveStatusScreenUseCase: SaveStatusScreenUseCase,
    private val router: Router,
    private val toaster: Toaster,
) : ViewModel() {


    var phone by mutableStateOf(TextFieldValue())
    var password by mutableStateOf(TextFieldValue())


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

    fun onBoardingScreenStatus(status: String) {
        saveStatusScreenUseCase.execute(status)
    }


}