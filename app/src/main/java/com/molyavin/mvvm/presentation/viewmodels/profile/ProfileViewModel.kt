package com.molyavin.mvvm.presentation.viewmodels.profile

import androidx.lifecycle.viewModelScope
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.domain.models.RouterNode
import com.molyavin.mvvm.domain.models.UserVM
import com.molyavin.mvvm.domain.usecase.sharedpref.GetUserVMUseCase
import com.molyavin.mvvm.presentation.controllers.auth.AuthorizationController
import com.molyavin.mvvm.presentation.controllers.settings.SettingController
import com.molyavin.mvvm.presentation.viewmodels.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getUserVMUseCase: GetUserVMUseCase,
    val router: Router,
) : BaseViewModel(router = router, toaster = null) {

    private val _userInfo = MutableStateFlow(UserVM.empty())
    val userInfo: StateFlow<UserVM> = _userInfo

    override fun onCreateView() {
        viewModelScope.launch {
            startCoroutine(runnable = {
                _userInfo.value = getUserVMUseCase.execute(null)
            }, onError = { exception ->
                showMessage("${exception?.message}")
            })

        }
    }

    fun logOut() {
        router.pushController(RouterTransaction.with(AuthorizationController()))
    }

    fun startSettings() {
        nextScreen(RouterNode(SettingController::class.java))
    }

}

