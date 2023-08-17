package com.molyavin.mvvm.presentation.screens.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.androidxtransition.R
import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.domain.usecase.ReadStatusAuntUseCase
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase
import com.molyavin.mvvm.domain.usecase.SaveStatusAuntUseCase
import com.molyavin.mvvm.presentation.screens.authorization.screen.AuthorizationController
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val readUserInfoUseCase: ReadUserInfoUseCase,
    private val saveStatusAuntUseCase: SaveStatusAuntUseCase,
    private val router: Router
) : ViewModel() {

    private val _userInfoLiveData = MutableLiveData<UserInfo>()
    val userInfoLiveData: LiveData<UserInfo> = _userInfoLiveData

    fun onCreate() {
        _userInfoLiveData.value = readUserInfoUseCase.execute(null)
    }

    fun exitFromProfile() {
        saveStatusAuntUseCase.execute("Off")
        router.pushController(RouterTransaction.with(AuthorizationController()))
        router.setRoot(RouterTransaction.with(AuthorizationController()))
    }


}