package com.molyavin.mvvm.presentation.screens.login.presenter

interface View {

    interface AuthorizationViewView : View {
        fun setErrorUserData()
    }

}