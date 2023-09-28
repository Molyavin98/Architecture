package com.molyavin.mvvm.presentation.viewmodels

import android.util.Log
import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.domain.models.RouterNode
import com.molyavin.mvvm.utils.Toaster
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    private val router: Router,
    private val toaster: Toaster? = null,
) : ViewModel() {

    private var isAttached = false

    protected val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _routerFlow = MutableSharedFlow<RouterNode<*>>(replay = 1)

    fun showMessage(message: String) {
        toaster?.show(message)
    }

    fun showLog(tag: String, message: String) {
        Log.d(tag, message)
    }

    fun navigateToBack() {
        router.handleBack()
    }

    fun attachRoot(controller: Controller) {
        router.setRoot(RouterTransaction.with(controller))
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

    // Navigation

    protected fun nextScreen(routerNode: RouterNode<*>) {
        _routerFlow.tryEmit(routerNode)
        _routerFlow.drop(1)

    }

    private fun startScreen(controller: Controller) {
        router.pushController(RouterTransaction.with(controller))
    }

    // Lifecycle

    @CallSuper
    open fun onCreateView() {
        viewModelScope.launch {
            _routerFlow
                .takeWhile { isAttached }
                .map { routerNode -> routerNode.controllerClass.newInstance() }
                .catch { it.printStackTrace() }
                .collect(this@BaseViewModel::startScreen)
        }
    }

    open fun onBindView(view: View) {}

    @CallSuper
    open fun onAttach() {
        isAttached = true
    }

    @CallSuper
    open fun onDetach() {
        isAttached = false
    }

    open fun onDestroyView() {}
    open fun onDestroy() {}

}