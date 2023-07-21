package com.molyavin.mvvm.presenter

interface MainContract {

    interface View {
        fun setErrorUserData()
    }

    interface Authorization {
        fun setUserData(phoneNumber: String, password: String)
        fun validateUserData(): Boolean
        fun onDestroy()
    }

}