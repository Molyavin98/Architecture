package com.molyavin.mvvm.data.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.molyavin.mvvm.data.model.NewUserDTO
import com.molyavin.mvvm.data.model.UserDTO

interface UserRepository {
    fun registerUser(data: NewUserDTO): Task<AuthResult>
    fun loginUser(data: NewUserDTO): Task<AuthResult>

    suspend fun saveUserDTO(data: UserDTO)
    suspend fun getUserDTO() : UserDTO

    suspend fun checkUserStatus(): Boolean
}