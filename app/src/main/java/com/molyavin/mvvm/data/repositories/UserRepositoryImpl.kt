package com.molyavin.mvvm.data.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.molyavin.mvvm.data.model.NewUserDTO
import com.molyavin.mvvm.data.model.UserDTO
import com.molyavin.mvvm.data.storage.DBSharedPreference
import com.molyavin.mvvm.utils.MissingUserInfoException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val fireBaseAunt: FirebaseAuth,
    private val dbSharedPreference: DBSharedPreference,
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

    override suspend fun saveUserDTO(data: UserDTO) {
        val json = Json.encodeToString(data)
        dbSharedPreference.saveData(USER_KEY, json)
    }

    override suspend fun getUserDTO(): UserDTO {
        val json = dbSharedPreference.getData(USER_KEY) ?: throw MissingUserInfoException()
        return Json.decodeFromString(json)
    }

    override suspend fun checkUserStatus(): Boolean {
        return false
    }

    companion object {

        private const val USER_KEY = "UserKey"
    }
}