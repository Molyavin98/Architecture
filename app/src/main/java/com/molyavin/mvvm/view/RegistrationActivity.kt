package com.molyavin.mvvm.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.molyavin.mvvm.databinding.ActivityRegistrationBinding
import com.molyavin.mvvm.viewmodel.RegistrationViewModel

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registrationViewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]
        registrationViewModel.initDB(this)

        binding.btnRegistration.setOnClickListener {

            if (registrationViewModel.checkField(
                    binding.textFieldFullName.editText?.text.toString(),
                    binding.textFieldPhone.editText?.text.toString(),
                    binding.textFieldPass.editText?.text.toString()
                )
            ) {
                registrationViewModel.setFullName(binding.textFieldFullName.editText?.text.toString())
                registrationViewModel.setPhone(binding.textFieldPhone.editText?.text.toString())
                registrationViewModel.setPassword(binding.textFieldPass.editText?.text.toString())

                registrationViewModel.saveDataUser()

                startActivity(Intent(this, AuthorizationActivity::class.java))
            } else {
                Toast.makeText(this, "Fields is not can empty!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}