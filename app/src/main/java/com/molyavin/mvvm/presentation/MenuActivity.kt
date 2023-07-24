package com.molyavin.mvvm.presentation

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.molyavin.mvvm.data.repositories.UserRepositoryImpl
import com.molyavin.mvvm.data.storage.DBSharedPreference
import com.molyavin.mvvm.databinding.ActivityMenuBinding
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase
import com.molyavin.mvvm.viewmodel.MenuViewModel
import com.molyavin.mvvm.viewmodel.MenuViewModelFactory

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userRepository = UserRepositoryImpl(DBSharedPreference(this))
        val readUserInfoUseCase = ReadUserInfoUseCase(userRepository)
        val viewModel = ViewModelProvider(
            this,
            MenuViewModelFactory(readUserInfoUseCase)
        )[MenuViewModel::class.java]

        viewModel.userInfoLiveData.observe(this) { userInfo ->

            binding.textFullName.text = "Full name: ${userInfo.fullName}"
            binding.textPhone.text = "Phone ${userInfo.phone}"
            binding.textPassword.text = "Password ${userInfo.password}"
        }

        viewModel.readUserData()
    }
}
