package com.arifwidayana.shared.data.local.repository

import com.arifwidayana.core.wrapper.DataResource
import com.arifwidayana.shared.data.local.datasource.UserPreferenceDatasource
import com.arifwidayana.shared.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

interface UserPreferenceRepository {
    suspend fun getUserToken(): Flow<DataResource<String>>
    suspend fun setUserToken(userToken: String): Flow<DataResource<Unit>>
    suspend fun deleteUserToken(): Flow<DataResource<Unit>>
}

class UserPreferenceRepositoryImpl(
    private val userPreferenceDatasource: UserPreferenceDatasource
) : UserPreferenceRepository, Repository() {
    override suspend fun getUserToken(): Flow<DataResource<String>> = flow {
        emit(proceed { userPreferenceDatasource.getUserToken().first() })
    }

    override suspend fun setUserToken(userToken: String): Flow<DataResource<Unit>> = flow {
        emit(proceed { userPreferenceDatasource.setUserToken(userToken) })
    }

    override suspend fun deleteUserToken(): Flow<DataResource<Unit>> = flow {
        emit(proceed { userPreferenceDatasource.deleteUserToken() })
    }
}