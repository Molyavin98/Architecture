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
import com.molyavin.mvvm.domain.di.component.AuthorizationComponent
import com.molyavin.mvvm.domain.di.component.DaggerAuthorizationComponent
import com.molyavin.mvvm.domain.di.component.Injector
import com.molyavin.mvvm.domain.di.modules.AuthorizationModule
import com.molyavin.mvvm.presentation.screens.login.presenter.AuthorizationPresenter
import com.molyavin.mvvm.utils.getTextString
import javax.inject.Inject

class AuthorizationController : Controller(),
    com.molyavin.mvvm.presentation.screens.login.presenter.View.AuthorizationViewView {


    private lateinit var component: AuthorizationComponent

    @Inject
    lateinit var presenter: AuthorizationPresenter

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

        val view = inflater.inflate(R.layout.controller_authorization, container, false)

        btnLogin = view.findViewById(R.id.btnLogin)
        btnRegistration = view.findViewById(R.id.btnRegistration)
        textFieldPhone = view.findViewById(R.id.textFieldPhone)
        textFieldPass = view.findViewById(R.id.textFieldPass)

        component = DaggerAuthorizationComponent.builder()
            .authorizationModule(AuthorizationModule(Injector.INSTANCE.getUserRepository()))
            .build()
        component.inject(this)

        presenter.onAttach(this)
        presenter.readDataUser()

        onClickListener()

        return view
    }

    private fun onClickListener() {

        btnLogin.setOnClickListener {

            if (presenter.validateUserData(
                    textFieldPhone.getTextString() ?: "",
                    textFieldPass.getTextString() ?: "",
                )
            ) {
                router.setRoot(RouterTransaction.with(MenuController()))
            } else {
                Toast.makeText(view?.context, "Data user is not correct!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        btnRegistration.setOnClickListener {
            router.pushController(RouterTransaction.with(RegistrationController()))
        }
    }

    override fun setErrorUserData() {

        if (textFieldPhone.getTextString().isNullOrEmpty() ||
            textFieldPass.getTextString().isNullOrEmpty()
        ) {
            Toast.makeText(view?.context, "Field is not can empty!", Toast.LENGTH_SHORT).show()
        }
    }


}