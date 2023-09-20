package com.molyavin.mvvm.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.molyavin.mvvm.data.model.DataStoreItemDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")

class DataStorePreference(private val context: Context) {

    suspend fun saveData(itemDto: DataStoreItemDTO) {
        context.dataStore.edit { pref ->
            itemDto.getItemInfo().forEach { (key, value) ->
                pref[stringPreferencesKey(key)] = value
            }
        }
    }

    suspend fun <T : DataStoreItemDTO> insertData(itemDto: T): T {
        val parameters = context
            .dataStore
            .data
            .map {
                it.asMap()
                    .mapKeys { entry -> entry.key.name }
                    .filterValues { value -> value is String }
                    .mapValues { value -> value.value as String }
            }
            .single()

        return itemDto.copyDTO(parameters) as T
    }


}