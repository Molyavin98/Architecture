package com.molyavin.mvvm.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase
import com.molyavin.mvvm.domain.usecase.SetStatusOnBoardingUseCase
import com.molyavin.mvvm.domain.usecase.SetStatusRememberMeUseCase
import com.molyavin.mvvm.presentation.controllers.MenuController
import com.molyavin.mvvm.utils.Toaster
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val readUserInfoUseCase: ReadUserInfoUseCase,
    private val setStatusOnBoardingUseCase: SetStatusOnBoardingUseCase,
    private val setStatusRememberMeUseCase: SetStatusRememberMeUseCase,
    router: Router,
    toaster: Toaster
) : BaseViewModel(router = router, toaster = toaster) {

    private var _phone = mutableStateOf(TextFieldValue())
    val phone: State<TextFieldValue> = _phone

    private var _password = mutableStateOf(TextFieldValue())
    var password: State<TextFieldValue> = _password

    private var _statusCheckBox = mutableStateOf(false)
    var statusCheckBox: State<Boolean> = _statusCheckBox

    fun login() {


        val readUserInfo = readUserInfoUseCase.execute(null)

        if (phone.value.text == readUserInfo.phone && password.value.text == readUserInfo.password) {
            startScreen(MenuController())
        } else if (phone.value.toString().isEmpty() || password.value.toString().isEmpty()) {
            showMessage("Field is not can empty!")
        } else {
            showMessage("Data user is not correct!")
        }

        statusRememberMe()
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