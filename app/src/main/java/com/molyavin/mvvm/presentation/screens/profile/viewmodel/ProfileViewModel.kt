package com.molyavin.mvvm.presentation.screens.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val readUserInfoUseCase: ReadUserInfoUseCase
) : ViewModel() {

    private val _userInfoLiveData = MutableLiveData<UserInfo>()
    val userInfoLiveData: LiveData<UserInfo> = _userInfoLiveData

    fun onCreate() {
        _userInfoLiveData.value = readUserInfoUseCase.execute(null)
    }

}