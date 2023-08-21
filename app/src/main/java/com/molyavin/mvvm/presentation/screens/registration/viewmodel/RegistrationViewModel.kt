package com.molyavin.mvvm.presentation.screens.registration.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.presentation.screens.authorization.screen.AuthorizationController
import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.domain.usecase.CheckFieldUseCase
import com.molyavin.mvvm.domain.usecase.SaveUserInfoUseCase
import com.molyavin.mvvm.utils.Toaster
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val saveUserInfoUseCase: SaveUserInfoUseCase,
    private val checkFieldUseCase: CheckFieldUseCase,
    private val router: Router,
    private val toaster: Toaster
) : ViewModel() {

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

            router.handleBack()
        } else {
            toaster.show("Fields cannot be empty!")
        }
    }
}