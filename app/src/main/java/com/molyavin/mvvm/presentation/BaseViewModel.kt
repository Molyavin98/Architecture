package com.molyavin.mvvm.presentation

import androidx.lifecycle.ViewModel
import com.bluelinelabs.conductor.Controller

abstract class BaseViewModel : ViewModel() {

    abstract fun startScreen()

    open fun attachRoot(controller:Controller){}

}