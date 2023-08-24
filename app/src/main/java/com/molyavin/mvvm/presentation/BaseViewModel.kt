package com.molyavin.mvvm.presentation

import android.view.View
import androidx.lifecycle.ViewModel
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.utils.Toaster

abstract class BaseViewModel(
    private val router: Router,
    private val toaster: Toaster?,
) : ViewModel() {

    open fun navigateToBack() {
        router.handleBack()
    }

    open fun showMessage(message: String) {
        toaster?.show(message)
    }

    open fun startScreen(controller: Controller) {
        router.pushController(RouterTransaction.with(controller))
    }

    open fun attachRoot(controller: Controller) {
        router.setRoot(RouterTransaction.with(controller))
    }

    open fun onAttach(view: View) {}
    open fun onDetach(view: View) {}
    open fun onDestroyView(view: View) {}
    open fun onDestroy() {}

}