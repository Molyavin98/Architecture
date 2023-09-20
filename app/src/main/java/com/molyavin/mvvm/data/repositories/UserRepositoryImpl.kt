package com.molyavin.mvvm.data.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.molyavin.mvvm.data.model.NewUserDTO
import com.molyavin.mvvm.data.model.UserDTO
import com.molyavin.mvvm.data.shared.UserDTOKeys
import com.molyavin.mvvm.data.storage.DataStorePreference
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val fireBaseAunt: FirebaseAuth,
    private val dataStorePreference: DataStorePreference,
) : UserRepository {

    override fun registerUser(user: NewUserDTO): Task<AuthResult> {
        return fireBaseAunt.createUserWithEmailAndPassword(
            user.email,
            user.password
        )
    }

    override fun loginUser(user: NewUserDTO): Task<AuthResult> {
        return fireBaseAunt.signInWithEmailAndPassword(user.email, user.password)
    }

    override suspend fun saveUserDTO(userDTO: UserDTO) {
        dataStorePreference.saveData(UserDTOKeys.getDataToSave(userDTO))
    }

    override suspend fun getUserDTO(): Flow<HashMap<String, String>> {
        return dataStorePreference.getData(UserDTOKeys.getListUserDTOKeys())
    }

    override suspend fun checkUserStatus(): Boolean {
        return false
    }

}