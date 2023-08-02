package com.molyavin.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.domain.usecase.SaveUserInfoUseCase
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(private val saveUserInfoUseCase: SaveUserInfoUseCase) :
    ViewModel() {

    private var fullName = MutableLiveData<String>()
    private var phone = MutableLiveData<String>()
    private var password = MutableLiveData<String>()

    fun checkField(fullName: String?, phone: String?, password: String?): Boolean {
        return !fullName.isNullOrEmpty() && !phone.isNullOrEmpty() && !password.isNullOrEmpty()
    }

    fun saveData(userFullName: String, userPhone: String, userPassword: String) {
        fullName.value = userFullName
        phone.value = userPhone
        password.value = userPassword

        saveUserInfoUseCase.execute(
            UserInfo(
                fullName.value.toString(),
                phone.value.toString(),
                password.value.toString()
            )
        )
    }
}