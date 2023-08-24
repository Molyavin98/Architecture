package com.molyavin.mvvm.presentation.screens.menu.viewmodel

import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.presentation.BaseViewModel
import com.molyavin.mvvm.utils.Toaster
import javax.inject.Inject

class MenuViewModel @Inject constructor(router: Router) :
    BaseViewModel(router = router, toaster = null)