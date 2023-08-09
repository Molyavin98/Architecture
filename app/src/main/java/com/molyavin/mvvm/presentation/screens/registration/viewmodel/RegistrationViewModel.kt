package com.molyavin.mvvm.presentation.screens.registration.viewmodel

import androidx.lifecycle.ViewModel
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.controlles.AuthorizationController
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

            router.pushController(RouterTransaction.with(AuthorizationController()))
        } else {
            toaster.show("Fields cannot be empty!")
        }


    }
}