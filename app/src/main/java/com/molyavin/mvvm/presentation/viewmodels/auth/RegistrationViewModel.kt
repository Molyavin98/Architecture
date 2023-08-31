package com.molyavin.mvvm.presentation.viewmodels.auth

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.domain.models.NewUserVM
import com.molyavin.mvvm.domain.usecase.auth.RegisterUserUseCase
import com.molyavin.mvvm.presentation.viewmodels.BaseViewModel
import com.molyavin.mvvm.utils.Toaster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    router: Router,
    toaster: Toaster
) : BaseViewModel(router, toaster) {

    private var _email = MutableStateFlow(TextFieldValue())
    var email: StateFlow<TextFieldValue> = _email

    private var _passwordOne = MutableStateFlow(TextFieldValue())
    var password: StateFlow<TextFieldValue> = _passwordOne

    private var _passwordTwo = MutableStateFlow(TextFieldValue())
    var passwordConfirm: StateFlow<TextFieldValue> = _passwordTwo

    fun registration() {

        val user = NewUserVM(
            email = email.value.text,
            password = password.value.text,
            passwordConfirm = passwordConfirm.value.text
        )

        viewModelScope.launch {
            startCoroutine(runnable = {
                if (registerUserUseCase.execute(user)) {
                    navigateToBack()
                }
            }, onError = { exception ->
                showMessage("${exception?.message}")
            })
        }

    }

    fun setPhone(phone: TextFieldValue) {
        _email.value = phone
    }

    fun setPasswordOne(password: TextFieldValue) {
        _passwordOne.value = password
    }

    fun setPasswordTwo(password: TextFieldValue) {
        _passwordTwo.value = password
    }
}