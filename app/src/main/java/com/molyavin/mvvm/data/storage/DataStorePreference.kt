package com.molyavin.mvvm.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")

class DataStorePreference(private val context: Context) {

    suspend fun saveData(data: HashMap<String, Any>) {
        context.dataStore.edit { pref ->
            data.forEach { (key, value) ->
                pref[stringPreferencesKey(key)] = value.toString()
            }
        }
    }

    fun getData(keys: List<String>): Flow<HashMap<String, String>> {
        return context.dataStore.data.map { pref ->
            val info = HashMap<String, String>()
            keys.forEach { key ->
                info[key] = pref[stringPreferencesKey(key)] ?: ""
            }
            info
        }
    }


}