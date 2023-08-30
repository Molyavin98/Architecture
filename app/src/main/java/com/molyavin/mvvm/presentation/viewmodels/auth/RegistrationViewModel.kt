package com.molyavin.mvvm.presentation.viewmodels.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.domain.models.NewUserVM
import com.molyavin.mvvm.domain.usecase.auth.RegisterUserUseCase
import com.molyavin.mvvm.presentation.viewmodels.BaseViewModel
import com.molyavin.mvvm.utils.Toaster
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    router: Router,
    toaster: Toaster
) : BaseViewModel(router, toaster) {

    private var _email = mutableStateOf(TextFieldValue())
    var email: State<TextFieldValue> = _email

    private var _passwordOne = mutableStateOf(TextFieldValue())
    var password: State<TextFieldValue> = _passwordOne

    private var _passwordTwo = mutableStateOf(TextFieldValue())
    var passwordConfirm: State<TextFieldValue> = _passwordTwo

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