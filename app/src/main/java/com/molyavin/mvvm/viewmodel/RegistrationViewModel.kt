package com.molyavin.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.domain.repository.UserRepository
import com.molyavin.mvvm.domain.usecase.SaveUserInfoUseCase

class RegistrationViewModel(private val userRepository: UserRepository) : ViewModel(),
    IRegistrationViewModel {

    private var fullName = MutableLiveData<String>()
    private var phone = MutableLiveData<String>()
    private var password = MutableLiveData<String>()
    override fun setUserData(userFullName: String, userPhone: String, userPassword: String) {
        fullName.value = userFullName
        phone.value = userPhone
        password.value = userPassword
    }

    override fun checkField(fullName: String, phone: String, password: String): Boolean {
        return fullName.isNotEmpty() && phone.isNotEmpty() && password.isNotEmpty()
    }

    override fun saveData() {

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