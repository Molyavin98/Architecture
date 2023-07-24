package com.molyavin.mvvm.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.molyavin.mvvm.data.repositories.UserRepositoryImpl
import com.molyavin.mvvm.data.storage.DBSharedPreference
import com.molyavin.mvvm.databinding.ActivityRegistrationBinding
import com.molyavin.mvvm.domain.models.UserInfo
import com.molyavin.mvvm.domain.usecase.SaveUserInfoUseCase
import com.molyavin.mvvm.utils.getTextString
import com.molyavin.mvvm.viewmodel.RegistrationViewModel
import com.molyavin.mvvm.viewmodel.RegistrationViewModelFactory

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userRepositoryImpl = UserRepositoryImpl(DBSharedPreference(this))
        val saveUserInfoUseCase = SaveUserInfoUseCase(userRepositoryImpl)
        val viewModelFactory = RegistrationViewModelFactory(saveUserInfoUseCase)
        val viewModel = ViewModelProvider(this, viewModelFactory)[RegistrationViewModel::class.java]

        binding.btnRegistration.setOnClickListener {
            val fullName = binding.textFieldFullName.getTextString()
            val phone = binding.textFieldPhone.getTextString()
            val password = binding.textFieldPass.getTextString()

            if (viewModel.checkField(fullName, phone, password)) {
                viewModel.saveData(fullName!!, phone!!, password!!)
                startActivity(Intent(this, AuthorizationActivity::class.java))
            } else {
                Toast.makeText(this, "Fields is not can empty!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}