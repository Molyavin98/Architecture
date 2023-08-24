package com.molyavin.mvvm.presentation.screens.profile.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase
import com.molyavin.mvvm.domain.usecase.SetStatusOnBoardingUseCase
import com.molyavin.mvvm.domain.usecase.SetStatusRememberMeUseCase
import com.molyavin.mvvm.presentation.screens.authorization.screen.AuthorizationController
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val readUserInfoUseCase: ReadUserInfoUseCase,
    private val setStatusRememberMeUseCase: SetStatusRememberMeUseCase,
    private val router: Router
) : ViewModel() {

    private val _userInfo = mutableStateOf(UserInfo.empty())
    val userInfo: State<UserInfo> = _userInfo
    fun onCreate() {
        _userInfo.value = readUserInfoUseCase.execute(null)
    }
    fun logOut() {
        setStatusRememberMeUseCase.execute("Off")
        router.pushController(RouterTransaction.with(AuthorizationController()))
    }

}