package com.molyavin.mvvm.presentation.viewmodels.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.domain.models.UserVM
import com.molyavin.mvvm.domain.usecase.sharedpref.GetUserVMUseCase
import com.molyavin.mvvm.presentation.controllers.auth.AuthorizationController
import com.molyavin.mvvm.presentation.viewmodels.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getUserVMUseCase: GetUserVMUseCase,
    val router: Router,
) : BaseViewModel(router = router, toaster = null) {

    private val _userInfo = mutableStateOf(UserVM.empty())
    val userInfo: State<UserVM> = _userInfo

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

}

