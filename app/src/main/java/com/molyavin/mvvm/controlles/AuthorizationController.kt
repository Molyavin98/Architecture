package com.molyavin.mvvm.controlles

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.compose.material3.Scaffold
import androidx.compose.ui.platform.ComposeView
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.google.android.material.textfield.TextInputLayout
import com.molyavin.mvvm.R
import com.molyavin.mvvm.domain.di.component.Injector
import com.molyavin.mvvm.presentation.screens.login.presenter.AuthorizationViewModel
import com.molyavin.mvvm.presentation.ui.theme.MVVMTheme
import com.molyavin.mvvm.utils.getTextString
import javax.inject.Inject

class AuthorizationController : Controller() {

    @Inject
    lateinit var viewModel: AuthorizationViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {

        Injector.INSTANCE.inject(this)

        val view = ComposeView(container.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        view.setContent {
            MVVMTheme {
                Scaffold {

                }
            }
        }

        viewModel.readDataUser()
        return view
    }

//    private fun onClickListener() {
//
//        btnLogin.setOnClickListener {
//            viewModel.login(
//                textFieldPhone.getTextString() ?: "",
//                textFieldPass.getTextString() ?: "",
//            )
//        }
//
//        btnRegistration.setOnClickListener {
//            router.pushController(RouterTransaction.with(RegistrationController()))
//        }
//    }

}