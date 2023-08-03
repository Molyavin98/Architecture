package com.molyavin.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.molyavin.mvvm.data.repositories.UserRepository
import com.molyavin.mvvm.domain.di.scope.ActivityScope
import com.molyavin.mvvm.domain.usecase.SaveUserInfoUseCase
import javax.inject.Inject

class RegistrationViewModelFactory constructor (private val saveUserInfoUseCase: SaveUserInfoUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)){
            return RegistrationViewModel(saveUserInfoUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}