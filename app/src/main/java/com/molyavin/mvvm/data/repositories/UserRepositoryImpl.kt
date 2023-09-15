package com.molyavin.mvvm.data.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.molyavin.mvvm.data.model.NewUserDTO
import com.molyavin.mvvm.data.model.UserDTO
import com.molyavin.mvvm.data.storage.DBSharedPreference
import com.molyavin.mvvm.utils.MissingUserInfoException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val fireBaseAunt: FirebaseAuth,
    private val dbSharedPreference: DBSharedPreference,
    moshi: Moshi,
) : UserRepository {

    private val userJsonAdapter: JsonAdapter<UserDTO> = moshi.adapter(UserDTO::class.java)

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
        val json = userJsonAdapter.toJson(data)
        dbSharedPreference.saveData(USER_KEY, json)
    }

    override suspend fun getUserDTO(): UserDTO {
        val json = dbSharedPreference.getData(USER_KEY) ?: throw MissingUserInfoException()
        return userJsonAdapter.fromJson(json) ?: throw MissingUserInfoException()
    }

    override suspend fun checkUserStatus(): Boolean {
        return false
    }

    companion object {

        private const val USER_KEY = "UserKey"
    }
}