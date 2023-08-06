package com.molyavin.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.controlles.AuthorizationController
import com.molyavin.mvvm.databinding.ActivityMainBinding

class ComponentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var router: Router
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        router = Conductor.attachRouter(this, binding.controllerContainer, savedInstanceState)
            .setPopRootControllerMode(Router.PopRootControllerMode.NEVER)

        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(AuthorizationController()))
        }

    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }
}