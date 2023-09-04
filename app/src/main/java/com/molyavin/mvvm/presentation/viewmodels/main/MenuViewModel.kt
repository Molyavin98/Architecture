package com.molyavin.mvvm.presentation.viewmodels.main

import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.domain.models.RouterNode
import com.molyavin.mvvm.presentation.controllers.profile.ProfileController
import com.molyavin.mvvm.presentation.controllers.settings.SettingController
import com.molyavin.mvvm.presentation.viewmodels.BaseViewModel
import javax.inject.Inject

class MenuViewModel @Inject constructor(router: Router) :
    BaseViewModel(router = router, toaster = null) {
    fun routeToUserProfile() {
        nextScreen(RouterNode(ProfileController::class.java))
    }
}