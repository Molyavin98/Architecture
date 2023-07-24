package com.molyavin.mvvm.presenter

import com.molyavin.mvvm.presentation.View

interface Presenter<T : View> {

    fun onAttach(view: T)
    fun onDestroy()

    interface Authorization : Presenter<View.AuthorizationViewView> {
        fun validateUserData(phoneUser: String, passwordUser: String): Boolean
    }

}