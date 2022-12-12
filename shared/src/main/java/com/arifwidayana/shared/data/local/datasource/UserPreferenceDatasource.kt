package com.arifwidayana.shared.data.local.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

interface UserPreferenceDatasource {
    suspend fun getUserToken(): Flow<String>
    suspend fun setUserToken(newUserToken: String)
    suspend fun deleteUserToken()
}

class UserPreferenceDatasourceImpl(
    private val dataStore: DataStore<Preferences>
) : UserPreferenceDatasource {
    override suspend fun getUserToken(): Flow<String> = flow {
        dataStore.data.map {
            it.toPreferences()[UserPreferenceKey.userToken].orEmpty()
        }
    }

    override suspend fun setUserToken(newUserToken: String) {
        dataStore.edit {
            it[UserPreferenceKey.userToken] = newUserToken
        }
    }

    override suspend fun deleteUserToken() {
        dataStore.edit {
            it.remove(UserPreferenceKey.userToken)
        }
    }
}