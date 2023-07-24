package com.molyavin.mvvm.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.controlles.MenuController
import com.molyavin.mvvm.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var router: Router

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        router = Conductor.attachRouter(this, binding.controllerContainer,savedInstanceState)
            .setPopRootControllerMode(Router.PopRootControllerMode.NEVER)

        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(MenuController()))
        }

       /* val userRepository = UserRepositoryImpl(DBSharedPreference(this))
        val readUserInfoUseCase = ReadUserInfoUseCase(userRepository)
        val viewModel = ViewModelProvider(
            this,
            MenuViewModelFactory(readUserInfoUseCase)
        )[MenuViewModel::class.java]*/

       /* viewModel.userInfoLiveData.observe(this) { userInfo ->

            binding.textFullName.text = "Full name: ${userInfo.fullName}"
            binding.textPhone.text = "Phone ${userInfo.phone}"
            binding.textPassword.text = "Password ${userInfo.password}"
        }

        viewModel.readUserData()*/
    }

    override fun onBackPressed() {
        if(!router.handleBack()) {
            super.onBackPressed()
        }
    }
}
