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
import com.molyavin.mvvm.utils.getTextString
import com.molyavin.mvvm.presentation.screens.registration.viewmodel.RegistrationViewModel
import javax.inject.Inject

class RegistrationController : Controller() {

    @Inject
    lateinit var viewModel: RegistrationViewModel
    private lateinit var view: View

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {

        Injector.INSTANCE.inject(this)

        view = inflater.inflate(R.layout.controller_registration, container, false)

        val btnRegistration: Button = view.findViewById(R.id.btnRegistration)
        val textFieldFullName: TextInputLayout = view.findViewById(R.id.textFieldFullName)
        val textFieldPhone: TextInputLayout = view.findViewById(R.id.textFieldPhone)
        val textFieldPass: TextInputLayout = view.findViewById(R.id.textFieldPass)

        btnRegistration.setOnClickListener {
            viewModel.saveData(
                textFieldFullName.getTextString(),
                textFieldPhone.getTextString(),
                textFieldPass.getTextString()
            )
        }

        return view
    }
}