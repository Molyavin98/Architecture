package com.molyavin.mvvm.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase

class MenuViewModelFactory(private val readUserInfoUseCase: ReadUserInfoUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)){
            return MenuViewModel(readUserInfoUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}