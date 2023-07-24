package com.molyavin.mvvm.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.molyavin.mvvm.presenter.MainContract
import com.molyavin.mvvm.data.repositories.UserRepositoryImpl
import com.molyavin.mvvm.databinding.ActivityAuthorizationBinding
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase
import com.molyavin.mvvm.presenter.AuthorizationPresenter

class AuthorizationActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityAuthorizationBinding
    private lateinit var presenter: AuthorizationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userRepositoryImpl = UserRepositoryImpl(this)
        presenter = AuthorizationPresenter(this, ReadUserInfoUseCase(userRepositoryImpl))
        presenter.readDataUser()

        onClickListener()

    }

    private fun onClickListener() {

        binding.btnLogin.setOnClickListener {

            if (presenter.validateUserData(
                    binding.textFieldPhone.editText?.text.toString(),
                    binding.textFieldPass.editText?.text.toString()
                )
            ) {
                startActivity(Intent(this, MenuActivity::class.java))
            } else {
                Toast.makeText(this, "Data user is not correct!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRegistration.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

    }

    override fun setErrorUserData() {

        if (binding.textFieldPhone.editText?.text.toString().isEmpty() ||
            binding.textFieldPass.editText?.text.toString().isEmpty()
        ) {
            Toast.makeText(this, "Field is not can empty!", Toast.LENGTH_SHORT).show()
        }
    }

}
