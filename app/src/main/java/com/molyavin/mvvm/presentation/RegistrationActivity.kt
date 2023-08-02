package com.molyavin.mvvm.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.molyavin.mvvm.databinding.ActivityRegistrationBinding
import com.molyavin.mvvm.domain.di.component.DaggerRegistrationComponent
import com.molyavin.mvvm.domain.di.component.Injector
import com.molyavin.mvvm.domain.di.component.RegistrationComponent
import com.molyavin.mvvm.domain.di.modules.RegistrationModule
import com.molyavin.mvvm.utils.getTextString
import com.molyavin.mvvm.viewmodel.RegistrationViewModel
import javax.inject.Inject

class RegistrationActivity : AppCompatActivity() {

    private lateinit var component: RegistrationComponent

    @Inject
    lateinit var viewModel: RegistrationViewModel

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        component = DaggerRegistrationComponent.builder()
            .registrationModule(RegistrationModule(Injector.INSTANCE.getUserRepository(), this))
            .build()

        component.inject(this)

        binding.btnRegistration.setOnClickListener {
            val fullName = binding.textFieldFullName.getTextString()
            val phone = binding.textFieldPhone.getTextString()
            val password = binding.textFieldPass.getTextString()
            if (viewModel.checkField(fullName, phone, password)) {
                viewModel.saveData(fullName!!, phone!!, password!!)
                startActivity(Intent(this, AuthorizationActivity::class.java))
            } else {
                Toast.makeText(this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
