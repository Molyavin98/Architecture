package com.molyavin.mvvm.data.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.molyavin.mvvm.data.model.DataStoreItemDTO
import com.molyavin.mvvm.data.model.NewUserDTO
import com.molyavin.mvvm.data.model.UserDTO
import com.molyavin.mvvm.data.storage.DataStorePreference
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val fireBaseAunt: FirebaseAuth,
    private val dataStorePreference: DataStorePreference,
) : UserRepository {

    override fun registerUser(data: NewUserDTO): Task<AuthResult> {
        return fireBaseAunt.createUserWithEmailAndPassword(
            data.email,
            data.password
        )
    }

    override fun loginUser(data: NewUserDTO): Task<AuthResult> {
        return fireBaseAunt.signInWithEmailAndPassword(data.email, data.password)
    }

    override suspend fun saveUserDTO(itemDto: DataStoreItemDTO) {
        dataStorePreference.saveData(itemDto)
    }

    override suspend fun getUserDTO(): UserDTO {
        return dataStorePreference.insertData(UserDTO.empty())
    }

    override suspend fun checkUserStatus(): Boolean {
        return false
    }

}