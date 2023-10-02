package com.molyavin.mvvm.presentation.viewmodels.profile

import android.view.View
import androidx.lifecycle.viewModelScope
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.domain.models.UserVM
import com.molyavin.mvvm.domain.usecase.auth.SetStatusRememberMeUseCase
import com.molyavin.mvvm.domain.usecase.sharedpref.GetUserVMUseCase
import com.molyavin.mvvm.presentation.controllers.auth.AuthorizationController
import com.molyavin.mvvm.presentation.controllers.info.InformationController
import com.molyavin.mvvm.presentation.controllers.profile.ProfileController
import com.molyavin.mvvm.presentation.viewmodels.BaseViewModel
import com.molyavin.mvvm.utils.Toaster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getUserVMUseCase: GetUserVMUseCase,
    private val setStatusRememberMeUseCase: SetStatusRememberMeUseCase,
    private val router: Router,
    toaster: Toaster,
) : BaseViewModel(router = router, toaster = toaster) {

    private val _userInfo = MutableStateFlow(UserVM.empty())
    val userInfo: StateFlow<UserVM> = _userInfo

    override fun onCreateView() {
        super.onCreateView()
        viewModelScope.launch {
            startCoroutine(runnable = {
                _userInfo.value = getUserVMUseCase.execute(null)
            }, onError = { exception ->
                showMessage("${exception?.message}")
            })
        }
    }

    fun nextScreenInfo() {
        router.pushController(RouterTransaction.with(InformationController()))
    }

    fun logOut() {
        setStatusRememberMeUseCase.execute(false)
        router.pushController(RouterTransaction.with(AuthorizationController()))
    }
}

