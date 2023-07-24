package com.molyavin.mvvm.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.molyavin.mvvm.data.repositories.UserRepositoryImpl
import com.molyavin.mvvm.databinding.ActivityRegistrationBinding
import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.viewmodel.RegistrationViewModel
import com.molyavin.mvvm.viewmodel.RegistrationViewModelFactory

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userRepositoryImpl = UserRepositoryImpl(this)
        val viewModelFactory = RegistrationViewModelFactory(userRepositoryImpl)
        val viewModel = ViewModelProvider(this, viewModelFactory)[RegistrationViewModel::class.java]

        binding.btnRegistration.setOnClickListener {

            val userInfo = UserInfo(
                binding.textFieldFullName.editText?.text.toString(),
                binding.textFieldPhone.editText?.text.toString(),
                binding.textFieldPass.editText?.text.toString()
            )

            if (viewModel.checkField(userInfo.fullName, userInfo.phone, userInfo.password)) {

                viewModel.setUserData(userInfo.fullName, userInfo.phone, userInfo.password)
                viewModel.saveData()

                startActivity(Intent(this, AuthorizationActivity::class.java))
            } else {
                Toast.makeText(this, "Fields is not can empty!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}