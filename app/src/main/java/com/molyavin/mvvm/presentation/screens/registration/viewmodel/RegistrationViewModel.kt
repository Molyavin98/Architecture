package com.molyavin.mvvm.presentation.screens.registration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.controlles.AuthorizationController
import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.domain.usecase.SaveUserInfoUseCase
import com.molyavin.mvvm.utils.Toaster
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val saveUserInfoUseCase: SaveUserInfoUseCase,
    private val router: Router,
    private val toaster: Toaster
) : ViewModel() {

    private var fullName = MutableLiveData<String>()
    private var phone = MutableLiveData<String>()
    private var password = MutableLiveData<String>()

    private fun checkField(fullName: String?, phone: String?, password: String?): Boolean {
        return !fullName.isNullOrEmpty() && !phone.isNullOrEmpty() && !password.isNullOrEmpty()
    }

    fun saveData(userFullName: String?, userPhone: String?, userPassword: String?) {
        fullName.value = userFullName
        phone.value = userPhone
        password.value = userPassword


        if (checkField(userFullName, userPhone, userPassword)) {
            saveUserInfoUseCase.execute(
                UserInfo(
                    fullName.value.toString(),
                    phone.value.toString(),
                    password.value.toString()
                )
            )
            router.pushController(RouterTransaction.with(AuthorizationController()))
        } else {
            toaster.show("Fields cannot be empty!")
        }


    }
}