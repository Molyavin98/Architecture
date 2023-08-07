package com.molyavin.mvvm.controlles

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.R

class SplashScreenController : Controller() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {

        Handler().postDelayed(Runnable {
            router.setRoot(RouterTransaction.with(AuthorizationController()))
            router.pushController(RouterTransaction.with(AuthorizationController()))

        }, 3000)

        return inflater.inflate(R.layout.activity_splash_screen, container, false)
    }
}