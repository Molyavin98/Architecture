package com.molyavin.mvvm.presentation.viewmodels.auth

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.domain.models.NewUserVM
import com.molyavin.mvvm.domain.models.RouterNode
import com.molyavin.mvvm.domain.usecase.auth.LoginUserUseCase
import com.molyavin.mvvm.domain.usecase.auth.SetStatusRememberMeUseCase
import com.molyavin.mvvm.domain.usecase.onboarding.SetStatusOnBoardingUseCase
import com.molyavin.mvvm.presentation.controllers.auth.RegistrationController
import com.molyavin.mvvm.presentation.controllers.main.MenuController
import com.molyavin.mvvm.presentation.viewmodels.BaseViewModel
import com.molyavin.mvvm.utils.Toaster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val setStatusOnBoardingUseCase: SetStatusOnBoardingUseCase,
    private val setStatusRememberMeUseCase: SetStatusRememberMeUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    router: Router,
    toaster: Toaster
) : BaseViewModel(router = router, toaster = toaster) {

    private var _email = MutableStateFlow(TextFieldValue())
    val email: StateFlow<TextFieldValue> = _email

    private var _password = MutableStateFlow(TextFieldValue())
    var password: StateFlow<TextFieldValue> = _password

    private var _statusCheckBox = MutableStateFlow(false)
    var statusCheckBox: StateFlow<Boolean> = _statusCheckBox

     fun login() {
        val user = NewUserVM(email = email.value.text, password = password.value.text)
        viewModelScope.launch {
            startCoroutine(runnable = {
                if (loginUserUseCase.execute(user)) {
                    _isLoading.value = true
                    statusRememberMe()
                    nextScreen(RouterNode(MenuController::class.java))
                    _isLoading.value = false
                }
            }, onError = { exception ->
                showMessage("${exception?.message}")
                exception?.printStackTrace()
            })
        }
    }

    fun startRegistrationController() {
        nextScreen(RouterNode(RegistrationController::class.java))
    }

    fun onBoardingScreenStatus(status: Boolean) {
        setStatusOnBoardingUseCase.execute(status)
    }

    private fun statusRememberMe() {
        setStatusRememberMeUseCase.execute(statusCheckBox.value)
    }

    fun setPhone(phone: TextFieldValue) {
        _email.value = phone
    }

    fun setPassword(password: TextFieldValue) {
        _password.value = password
    }

    fun setStatusCheckBox(status: Boolean) {
        _statusCheckBox.value = status
    }

}