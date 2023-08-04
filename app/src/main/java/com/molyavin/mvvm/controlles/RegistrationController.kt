package com.molyavin.mvvm.controlles

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.google.android.material.textfield.TextInputLayout
import com.molyavin.mvvm.MainActivity
import com.molyavin.mvvm.R
import com.molyavin.mvvm.domain.di.component.DaggerRegistrationComponent
import com.molyavin.mvvm.domain.di.component.Injector
import com.molyavin.mvvm.domain.di.component.RegistrationComponent
import com.molyavin.mvvm.domain.di.modules.RegistrationModule
import com.molyavin.mvvm.utils.getTextString
import com.molyavin.mvvm.viewmodel.RegistrationViewModel
import javax.inject.Inject

class RegistrationController : Controller() {

    private lateinit var component: RegistrationComponent

    @Inject
    lateinit var viewModel: RegistrationViewModel

    private lateinit var btnRegistration: Button
    private lateinit var textFieldFullName: TextInputLayout
    private lateinit var textFieldPhone: TextInputLayout
    private lateinit var textFieldPass: TextInputLayout

    private lateinit var view: View


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {

        view = inflater.inflate(R.layout.controller_registration, container, false)

        btnRegistration = view.findViewById(R.id.btnRegistration)
        textFieldFullName = view.findViewById(R.id.textFieldFullName)
        textFieldPhone = view.findViewById(R.id.textFieldPhone)
        textFieldPass = view.findViewById(R.id.textFieldPass)

        val viewModelStoreOwner: ViewModelStoreOwner = (activity) as MainActivity

        component = DaggerRegistrationComponent.builder()
            .registrationModule(
                RegistrationModule(
                    Injector.INSTANCE.getUserRepository(), viewModelStoreOwner
                )
            ).build()
        component.inject(this)


        onClickListener()

        return view
    }
    private fun onClickListener() {

        btnRegistration.setOnClickListener {
            val fullName = textFieldFullName.getTextString()
            val phone = textFieldPhone.getTextString()
            val password = textFieldPass.getTextString()
            if (viewModel.checkField(fullName, phone, password)) {
                viewModel.saveData(fullName!!, phone!!, password!!)
                router.pushController(RouterTransaction.with(AuthorizationController()))
            } else {
                Toast.makeText(view.context, "Fields cannot be empty!", Toast.LENGTH_SHORT).show()
            }
        }
    }


}