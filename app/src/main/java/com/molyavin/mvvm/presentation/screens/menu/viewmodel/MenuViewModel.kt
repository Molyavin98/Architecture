package com.molyavin.mvvm.presentation.screens.menu.viewmodel

import androidx.lifecycle.ViewModel
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.presentation.screens.profile.screen.ProfileController
import javax.inject.Inject

class MenuViewModel @Inject constructor(val router: Router) : ViewModel() {

    fun startScreenProfile(){
        router.pushController(RouterTransaction.with(ProfileController()))
    }

}