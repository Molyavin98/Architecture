package com.molyavin.mvvm.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.molyavin.mvvm.model.DBSharedPreference

class RegistrationViewModel : ViewModel() {

    private lateinit var db: DBSharedPreference
    private var fullName = MutableLiveData<String>()
    private var phone = MutableLiveData<String>()
    private var password = MutableLiveData<String>()

    fun initDB(context: Context) {
        db = DBSharedPreference(context)
    }

    fun setFullName(userFullName: String) {
        fullName.value = userFullName
    }

    fun setPhone(userPhone: String) {
        phone.value = userPhone
    }

    fun setPassword(userPassword: String) {
        password.value = userPassword
    }

    fun checkField(fullName:String,phone:String, password:String) : Boolean{

        if (fullName.isNotEmpty() && phone.isNotEmpty() && password.isNotEmpty()) {
            return true
        }
        return false
    }

    fun saveDataUser() {
        db.saveData("fullName", fullName.value.toString())
        db.saveData("phone", phone.value.toString())
        db.saveData("password", password.value.toString())
    }

}