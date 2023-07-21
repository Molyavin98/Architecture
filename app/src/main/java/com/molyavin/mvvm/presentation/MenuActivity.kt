package com.molyavin.mvvm.presentation

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.molyavin.mvvm.databinding.ActivityMenuBinding
class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("DataBase", MODE_PRIVATE)

        binding.textFullName.text = "Full name: ${sharedPreferences.getString("FullName", "")}"
        binding.textPhone.text = "Phone ${sharedPreferences.getString("Phone", "")}"
        binding.textPassword.text = "Password ${sharedPreferences.getString("Password", "")}"
    }
}