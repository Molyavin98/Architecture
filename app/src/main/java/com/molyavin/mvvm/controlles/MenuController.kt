package com.molyavin.mvvm.controlles

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.molyavin.mvvm.R

class MenuController : Controller() {

    private lateinit var btnProfile: ImageButton
    private lateinit var btnSetting: ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.controller_menu, container, false)

        btnProfile = view.findViewById(R.id.btnProfile)
        btnSetting = view.findViewById(R.id.btnSettings)


        btnProfile.setOnClickListener {
            router.pushController(RouterTransaction.with(ProfileController()))
        }

        btnSetting.setOnClickListener { }

        return view
    }

}