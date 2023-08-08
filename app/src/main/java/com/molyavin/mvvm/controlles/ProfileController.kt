package com.molyavin.mvvm.controlles

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bluelinelabs.conductor.Controller
import com.molyavin.mvvm.MainActivity
import com.molyavin.mvvm.R
import com.molyavin.mvvm.domain.di.component.Injector
import com.molyavin.mvvm.presentation.screens.profile.viewmodel.ProfileViewModel
import javax.inject.Inject

class ProfileController : Controller() {

    @Inject
    lateinit var viewModel: ProfileViewModel

    @SuppressLint("SetTextI18n", "NewApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {

        Injector.INSTANCE.inject(this)

        val view = inflater.inflate(R.layout.controller_user_info, container, false)

        val textFullName: TextView = view.requireViewById(R.id.textFullName)
        val textPhone: TextView = view.requireViewById(R.id.textPhone)
        val textPassword: TextView = view.requireViewById(R.id.textPassword)

        viewModel.userInfoLiveData.observe(activity as MainActivity) { userInfo ->
            textFullName.text = "Full name: ${userInfo.fullName}"
            textPhone.text = "Phone ${userInfo.phone}"
            textPassword.text = "Password ${userInfo.password}"
        }

        viewModel.onCreate()

        return view
    }
}