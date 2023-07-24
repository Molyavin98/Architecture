package com.molyavin.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.domain.usecase.IUseCase
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase

class MenuViewModel(private val readUserInfoUseCase: IUseCase<Any?, UserInfo>) : ViewModel() {

    private val _userInfoLiveData = MutableLiveData<UserInfo>()
    val userInfoLiveData: LiveData<UserInfo> = _userInfoLiveData

    fun readUserData() {
        _userInfoLiveData.value = readUserInfoUseCase.execute(null)
    }


}