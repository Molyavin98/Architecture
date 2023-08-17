package com.molyavin.mvvm.presentation.screens.authorization.presenter

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase
import com.molyavin.mvvm.domain.usecase.SetStatusOnBoardingUseCase
import com.molyavin.mvvm.domain.usecase.SetStatusRememberMeUseCase
import com.molyavin.mvvm.presentation.screens.authorization.screen.AuthorizationController
import com.molyavin.mvvm.presentation.screens.menu.screen.MenuController
import com.molyavin.mvvm.presentation.screens.registration.screen.RegistrationController
import com.molyavin.mvvm.utils.Toaster
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val readUserInfoUseCase: ReadUserInfoUseCase,
    private val setStatusOnBoardingUseCase: SetStatusOnBoardingUseCase,
    private val setStatusRememberMeUseCase: SetStatusRememberMeUseCase,
    private val router: Router,
    private val toaster: Toaster,
) : ViewModel() {

    private var _phone = mutableStateOf(TextFieldValue())
    val phone: State<TextFieldValue> = _phone

    private var _password = mutableStateOf(TextFieldValue())
    var password: State<TextFieldValue> = _password

    private var _statusRememberMe = mutableStateOf(false)
    var statusRememberMe: State<Boolean> = _statusRememberMe

    fun login() {

        if (phone.value.text == readUserInfoUseCase.execute(null).phone &&
            password.value.text == readUserInfoUseCase.execute(null).password
        ) {
            router.pushController(RouterTransaction.with(MenuController()))
        } else if (phone.value.toString().isEmpty() || password.value.toString().isEmpty()) {
            toaster.show("Field is not can empty!")
        } else {
            toaster.show("Data user is not correct!")
        }

        statusRememberMe()
    }
    fun attachRoot(authorizationController: AuthorizationController) {
        router.setRoot(RouterTransaction.with(authorizationController))
    }

    fun goToRegistrationScreen() {
        router.pushController(RouterTransaction.with(RegistrationController()))
    }

    fun onBoardingScreenStatus(status: String) {
        setStatusOnBoardingUseCase.execute(status)
    }

    private fun statusRememberMe() {
        if (statusRememberMe.value) {
            setStatusRememberMeUseCase.execute("On")
        } else {
            setStatusRememberMeUseCase.execute("Off")
        }

    }
    fun setPhone(phone: TextFieldValue) {
        _phone.value = phone
    }

    fun setPassword(password: TextFieldValue) {
        _password.value = password
    }

    fun setStatusRememberMe(status: Boolean) {
        _statusRememberMe.value = status
    }


}