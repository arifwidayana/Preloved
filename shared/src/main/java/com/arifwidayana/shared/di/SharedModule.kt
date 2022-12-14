package com.arifwidayana.shared.di

import com.arifwidayana.core.base.BaseModule
import com.arifwidayana.shared.data.local.datasource.UserPreferenceDatasource
import com.arifwidayana.shared.data.local.datasource.UserPreferenceDatasourceImpl
import com.arifwidayana.shared.data.local.datasource.UserPreferenceFactory
import com.arifwidayana.shared.data.local.repository.UserPreferenceRepository
import com.arifwidayana.shared.data.local.repository.UserPreferenceRepositoryImpl
import com.arifwidayana.shared.data.network.NetworkClient
import com.arifwidayana.shared.domain.GetUserTokenUseCase
import com.arifwidayana.shared.domain.SetUserTokenUseCase
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

object SharedModule : BaseModule {
    private val local = module {
        single { UserPreferenceFactory(androidContext()).create() }
    }

    private val network = module {
        single { ChuckerInterceptor.Builder(androidContext()).build() }
        single { NetworkClient(get(), get()) }
    }

    private val datasource = module {
        single<UserPreferenceDatasource> { UserPreferenceDatasourceImpl(get()) }
    }

    private val repository = module {
        single<UserPreferenceRepository> { UserPreferenceRepositoryImpl(get()) }
    }

    private val useCase = module {
        single { SetUserTokenUseCase(get(), Dispatchers.IO) }
        single { GetUserTokenUseCase(get(), Dispatchers.IO) }
    }

    private val common = module {
        single { Gson() }
    }

    override fun getModules(): List<Module> =
        listOf(local, network, datasource, repository, useCase, common)
}