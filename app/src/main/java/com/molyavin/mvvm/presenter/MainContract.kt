package com.molyavin.mvvm.presenter

interface MainContract {

    interface View {
        fun setErrorUserData()
    }

    interface Authorization {
        fun validateUserData(phoneUser: String, passwordUser: String): Boolean
        fun onDestroy()
    }

}