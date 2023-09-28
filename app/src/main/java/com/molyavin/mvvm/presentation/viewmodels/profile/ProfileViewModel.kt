package com.molyavin.mvvm.presentation.viewmodels.profile

import android.view.View
import androidx.lifecycle.viewModelScope
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.domain.models.RouterNode
import com.molyavin.mvvm.domain.models.UserVM
import com.molyavin.mvvm.domain.usecase.auth.SetStatusRememberMeUseCase
import com.molyavin.mvvm.domain.usecase.sharedpref.GetUserVMUseCase
import com.molyavin.mvvm.presentation.controllers.auth.AuthorizationController
<<<<<<< HEAD
import com.molyavin.mvvm.presentation.controllers.settings.SettingController
=======
import com.molyavin.mvvm.presentation.controllers.info.InformationController
import com.molyavin.mvvm.presentation.controllers.profile.ProfileController
>>>>>>> 0858822 (Implementation epoxy lib in project)
import com.molyavin.mvvm.presentation.viewmodels.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getUserVMUseCase: GetUserVMUseCase,
    private val setStatusRememberMeUseCase: SetStatusRememberMeUseCase,
    val router: Router,
) : BaseViewModel(router = router, toaster = null) {

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


    fun startSettings() {
        nextScreen(RouterNode(SettingController::class.java))
    }

}

