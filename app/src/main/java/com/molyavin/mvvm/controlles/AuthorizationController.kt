package com.molyavin.mvvm.controlles

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.google.android.material.textfield.TextInputLayout
import com.molyavin.mvvm.R
import com.molyavin.mvvm.domain.di.component.Injector
import com.molyavin.mvvm.presentation.screens.login.presenter.AuthorizationViewModel
import com.molyavin.mvvm.utils.getTextString
import javax.inject.Inject

class AuthorizationController : Controller() {

    @Inject
    lateinit var viewModel: AuthorizationViewModel

    private lateinit var btnLogin: Button
    private lateinit var btnRegistration: Button
    private lateinit var textFieldPhone: TextInputLayout
    private lateinit var textFieldPass: TextInputLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {

        Injector.INSTANCE.inject(this)

        val view = inflater.inflate(R.layout.controller_authorization, container, false)

        btnLogin = view.findViewById(R.id.btnLogin)
        btnRegistration = view.findViewById(R.id.btnRegistration)
        textFieldPhone = view.findViewById(R.id.textFieldPhone)
        textFieldPass = view.findViewById(R.id.textFieldPass)

        viewModel.readDataUser()

        onClickListener()

        return view
    }

    private fun onClickListener() {

        btnLogin.setOnClickListener {
            viewModel.login(
                textFieldPhone.getTextString() ?: "",
                textFieldPass.getTextString() ?: "",
            )
        }

        btnRegistration.setOnClickListener {
            router.pushController(RouterTransaction.with(RegistrationController()))
        }
    }

}