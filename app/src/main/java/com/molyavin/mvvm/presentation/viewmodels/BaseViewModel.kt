package com.molyavin.mvvm.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.utils.Toaster
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    private val router: Router,
    private val toaster: Toaster? = null,
) : ViewModel() {

    protected val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    open fun navigateToBack() {
        router.handleBack()
    }

    open fun showMessage(message: String) {
        toaster?.show(message)
    }

    open fun showLog(tag: String, message: String) {
        Log.d(tag, message)
    }

    open fun startScreen(controller: Controller) {
        router.pushController(RouterTransaction.with(controller))
    }

    open fun attachRoot(controller: Controller) {
        router.setRoot(RouterTransaction.with(controller))
    }

    open fun onCreateView() {
    }

    protected suspend fun startCoroutine(
        runnable: suspend () -> Unit,
        onError: ((Throwable?) -> Unit)? = null,
    ) {
        try {
            runnable()
        } catch (t: Throwable) {
            onError?.invoke(t)
        }
    }

    open fun onAttach() {}
    open fun onDetach() {}
    open fun onDestroyView() {}
    open fun onDestroy() {}

}