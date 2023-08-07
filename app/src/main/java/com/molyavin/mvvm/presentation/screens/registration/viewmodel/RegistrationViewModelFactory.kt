package com.molyavin.mvvm.presentation.screens.registration.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.molyavin.mvvm.domain.usecase.SaveUserInfoUseCase
import javax.inject.Inject

class RegistrationViewModelFactory @Inject constructor (private val saveUserInfoUseCase: SaveUserInfoUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)){
            return RegistrationViewModel(saveUserInfoUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}