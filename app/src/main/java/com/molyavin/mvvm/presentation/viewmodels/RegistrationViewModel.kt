package com.molyavin.mvvm.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.domain.usecase.CheckFieldUseCase
import com.molyavin.mvvm.domain.usecase.SaveUserInfoUseCase
import com.molyavin.mvvm.utils.Toaster
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val saveUserInfoUseCase: SaveUserInfoUseCase,
    private val checkFieldUseCase: CheckFieldUseCase,
    router: Router,
    toaster: Toaster
) : BaseViewModel(router, toaster) {

   private var _phone = mutableStateOf(TextFieldValue())
    var phone: State<TextFieldValue> = _phone

    private var _passwordOne = mutableStateOf(TextFieldValue())
    var passwordOne:State<TextFieldValue> = _passwordOne

    private var _passwordTwo = mutableStateOf(TextFieldValue())
    var passwordTwo:State<TextFieldValue> = _passwordTwo

    fun saveData() {

        if (checkFieldUseCase.execute(
                UserInfo(
                    phone = phone.value.text,
                    passwordOne = passwordOne.value.text,
                    passwordTwo = passwordTwo.value.text
                )
            )
        ) {
            saveUserInfoUseCase.execute(
                UserInfo(
                    phone = phone.value.text,
                    passwordOne = passwordOne.value.text,
                    passwordTwo = passwordTwo.value.text
                )
            )
            navigateToBack()
        } else {
            showMessage("Fields cannot be empty!")
        }
    }


    fun setPhone(phone: TextFieldValue){
        _phone.value = phone
    }
    fun setPasswordOne(password: TextFieldValue){
        _passwordOne.value = password
    }
    fun setPasswordTwo(password: TextFieldValue){
        _passwordTwo.value = password
    }
}