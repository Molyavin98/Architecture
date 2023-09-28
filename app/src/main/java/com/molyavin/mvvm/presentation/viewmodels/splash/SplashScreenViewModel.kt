package com.molyavin.mvvm.presentation.viewmodels.splash

import com.bluelinelabs.conductor.Router
import com.molyavin.mvvm.domain.usecase.splash.StartScreenUseCase
import com.molyavin.mvvm.presentation.viewmodels.BaseViewModel
import com.molyavin.mvvm.utils.AppDispatchers
import com.molyavin.mvvm.utils.Toaster
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val startScreenUseCase: StartScreenUseCase,
    private val dispatchers: AppDispatchers,
    val router: Router,
    toaster: Toaster,
) : BaseViewModel(router = router, toaster = toaster) {

    private var disposable: Disposable? = null

    override fun onCreateView() {
        super.onCreateView()
        findNextRouterNode()
    }

    private fun findNextRouterNode() {
        disposable = startScreenUseCase.execute(null)
            .subscribeOn(dispatchers.rxIo)
            .observeOn(dispatchers.rxMain)
            .subscribe(this::nextScreen) {
                it.printStackTrace()
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

}