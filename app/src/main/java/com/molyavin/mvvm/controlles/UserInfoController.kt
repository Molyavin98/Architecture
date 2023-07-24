package com.molyavin.mvvm.controlles

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.bluelinelabs.conductor.Controller
import com.molyavin.mvvm.R
import com.molyavin.mvvm.data.repositories.UserRepositoryImpl
import com.molyavin.mvvm.data.storage.DBSharedPreference
import com.molyavin.mvvm.domain.usecase.ReadUserInfoUseCase
import com.molyavin.mvvm.presentation.MenuActivity
import com.molyavin.mvvm.viewmodel.MenuViewModel
import com.molyavin.mvvm.viewmodel.MenuViewModelFactory

class UserInfoController : Controller() {

    private lateinit var textFullName: TextView
    private lateinit var textPhone: TextView
    private lateinit var textPassword: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.controller_user_info, container, false)

        textFullName = view.requireViewById(R.id.textFullName)
        textPhone = view.requireViewById(R.id.textPhone)
        textPassword = view.requireViewById(R.id.textPassword)

        val userRepository = UserRepositoryImpl(DBSharedPreference(view.context))
        val readUserInfoUseCase = ReadUserInfoUseCase(userRepository)

        val viewModelStoreOwner: ViewModelStoreOwner = (activity) as MenuActivity

        val viewModel = ViewModelProvider(
            viewModelStoreOwner,
            MenuViewModelFactory (readUserInfoUseCase)
        )[MenuViewModel::class.java]

        viewModel.userInfoLiveData.observe(activity as MenuActivity) { userInfo ->

            textFullName.text = "Full name: ${userInfo.fullName}"
            textPhone.text = "Phone ${userInfo.phone}"
            textPassword.text = "Password ${userInfo.password}"
        }

        viewModel.readUserData()


        return view
    }
}