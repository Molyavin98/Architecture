package com.molyavin.mvvm.presentation.screens.registration.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.domain.usecase.CheckFieldUseCase
import com.molyavin.mvvm.domain.usecase.SaveUserInfoUseCase
import com.molyavin.mvvm.presentation.BaseViewModel
import com.molyavin.mvvm.utils.Toaster
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val saveUserInfoUseCase: SaveUserInfoUseCase,
    private val checkFieldUseCase: CheckFieldUseCase,
    router: Router,
    toaster: Toaster
) : BaseViewModel(router, toaster) {

    var phone by mutableStateOf(TextFieldValue())
    var passwordOne by mutableStateOf(TextFieldValue())
    var passwordTwo by mutableStateOf(TextFieldValue())

    fun saveData(userFullName: String?, userPhone: String?, userPassword: String?) {

        if (checkFieldUseCase.execute(
                UserInfo(
                    fullName = userFullName!!,
                    phone = userPhone!!,
                    password = userPassword!!
                )
            )

        ) {
            saveUserInfoUseCase.execute(
                UserInfo(
                    fullName = userFullName,
                    phone = userPhone,
                    password = userPassword
                )
            )
            navigateToBack()
        } else {
            showMessage("Fields cannot be empty!")
        }
    }
}