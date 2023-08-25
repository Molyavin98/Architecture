package com.molyavin.mvvm.presentation.viewmodels

import com.bluelinelabs.conductor.Router
import javax.inject.Inject

class MenuViewModel @Inject constructor(router: Router) :
    BaseViewModel(router = router, toaster = null)