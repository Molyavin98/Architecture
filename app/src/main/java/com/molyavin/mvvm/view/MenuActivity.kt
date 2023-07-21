package com.molyavin.mvvm.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.molyavin.mvvm.R
import com.molyavin.mvvm.databinding.ActivityMenuBinding
import com.molyavin.mvvm.model.DBSharedPreference

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DBSharedPreference(this)

        binding.textFullName.text = "Full name: ${db.getData("fullName")}"
        binding.textPhone.text = "Phone ${db.getData("phone")}"
        binding.textPassword.text = "Password ${db.getData("password")}"
    }
}