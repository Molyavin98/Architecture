package com.molyavin.mvvm.data.repositories

import com.molyavin.mvvm.data.storage.DBSharedPreference
import com.molyavin.mvvm.utils.Constants.USER_VM_KEY
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(private val db: DBSharedPreference) :
    WordRepository {

    override fun saveId(id: String) {
        db.saveData(USER_VM_KEY, id)
    }

    override fun getID(): Int {
        return db.getData(USER_VM_KEY)!!.toInt()
    }
}