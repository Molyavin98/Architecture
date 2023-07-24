package com.molyavin.mvvm.controlles

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.R

class MenuController : Controller() {

    private lateinit var btnShowUserInfo: Button
    private lateinit var btnSetting: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.controller_menu, container, false)

        btnShowUserInfo = view.findViewById(R.id.btnShowUserInfo)
        btnSetting = view.findViewById(R.id.btnSettings)


        btnShowUserInfo.setOnClickListener {
            router.pushController(RouterTransaction.with(UserInfoController()))
        }

        btnSetting.setOnClickListener { }

        return view
    }
}