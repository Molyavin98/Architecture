package com.molyavin.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.domain.repository.UserRepository
import com.molyavin.mvvm.domain.usecase.SaveUserInfoUseCase

class RegistrationViewModel : ViewModel() {

    private var fullName = MutableLiveData<String>()
    private var phone = MutableLiveData<String>()
    private var password = MutableLiveData<String>()
    fun setUserData(userFullName: String, userPhone: String, userPassword: String) {
        fullName.value = userFullName
        phone.value = userPhone
        password.value = userPassword
    }
    fun checkField(fullName: String, phone: String, password: String): Boolean {

        if (fullName.isNotEmpty() && phone.isNotEmpty() && password.isNotEmpty()) {
            return true
        }
        return false
    }
    fun saveData(userRepository: UserRepository) {

        val saveUserInfoUseCase = SaveUserInfoUseCase(userRepository)

        saveUserInfoUseCase.execute(
            UserInfo(
                fullName.value.toString(),
                phone.value.toString(),
                password.value.toString()
            )
        )
    }
}