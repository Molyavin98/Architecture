package com.molyavin.mvvm

interface MainContract {

    interface View {
        fun setErrorUserData()
    }

    interface Authorization {
        fun setUserData(phoneNumber: String, password: String)
        fun validateUserData(): Boolean
        fun onDestroy()
    }

    interface Model {
        fun saveData(key: String, data: String)
        fun getData(key: String): String?
    }

}