package com.molyavin.mvvm.presentation

interface View {

    interface AuthorizationViewView : View {
        fun setErrorUserData()
    }

}