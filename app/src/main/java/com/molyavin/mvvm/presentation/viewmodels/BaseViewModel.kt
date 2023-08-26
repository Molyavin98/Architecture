package com.molyavin.mvvm.presentation.viewmodels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.ViewModel
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.utils.Toaster
import javax.inject.Inject

open class BaseViewModel(
    private val router: Router,
    private val toaster: Toaster? = null
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

    open fun onCreateView(
        inflater: LayoutInflater?,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {
        TODO()
    }

    open fun onAttach(view: View) {}
    open fun onDetach(view: View) {}
    open fun onDestroyView(view: View) {}
    open fun onDestroy() {}

}