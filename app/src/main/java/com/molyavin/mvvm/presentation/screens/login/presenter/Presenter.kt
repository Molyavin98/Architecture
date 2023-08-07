package com.molyavin.mvvm.presentation.screens.login.presenter

interface Presenter<T : View> {

    fun onAttach(view: T)
    fun onDestroy()

    interface Authorization : Presenter<View.AuthorizationViewView> {
        fun validateUserData(phoneUser: String, passwordUser: String): Boolean
    }

}