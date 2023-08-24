package com.molyavin.mvvm.presentation.screens.authorization.presenter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.presentation.BaseViewModel
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase
import com.molyavin.mvvm.domain.usecase.SetStatusOnBoardingUseCase
import com.molyavin.mvvm.domain.usecase.SetStatusRememberMeUseCase
import com.molyavin.mvvm.presentation.screens.menu.screen.MenuController
import com.molyavin.mvvm.presentation.screens.registration.screen.RegistrationController
import com.molyavin.mvvm.utils.Toaster
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val readUserInfoUseCase: ReadUserInfoUseCase,
    private val setStatusOnBoardingUseCase: SetStatusOnBoardingUseCase,
    private val setStatusRememberMeUseCase: SetStatusRememberMeUseCase,
    private val router: Router,
    private val toaster: Toaster,
) : BaseViewModel() {

    private var _phone = mutableStateOf(TextFieldValue())
    val phone: State<TextFieldValue> = _phone

    private var _password = mutableStateOf(TextFieldValue())
    var password: State<TextFieldValue> = _password

    private var _statusCheckBox = mutableStateOf(false)
    var statusCheckBox: State<Boolean> = _statusCheckBox

    fun login() {

        val readUserInfo = readUserInfoUseCase.execute(null)

        if (phone.value.text == readUserInfo.phone && password.value.text == readUserInfo.password) {
            router.pushController(RouterTransaction.with(MenuController()))
        } else if (phone.value.toString().isEmpty() || password.value.toString().isEmpty()) {
            toaster.show("Field is not can empty!")
        } else {
            toaster.show("Data user is not correct!")
        }

        statusRememberMe()
    }

    override fun attachRoot(controller: Controller) {
        router.setRoot(RouterTransaction.with(controller))
    }

    override fun startScreen() {
        router.pushController(RouterTransaction.with(RegistrationController()))
    }

    fun onBoardingScreenStatus(status: String) {
        setStatusOnBoardingUseCase.execute(status)
    }

    private fun statusRememberMe() {

        if (statusCheckBox.value) {
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

    fun setStatusCheckBox(status: Boolean) {
        _statusCheckBox.value = status
    }

}