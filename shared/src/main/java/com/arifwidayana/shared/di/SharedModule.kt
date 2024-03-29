package com.arifwidayana.shared.di

import androidx.room.Room
import com.arifwidayana.core.base.BaseModule
import com.arifwidayana.shared.data.local.PrelovedDatabase
import com.arifwidayana.shared.data.local.datasource.*
import com.arifwidayana.shared.data.network.NetworkClient
import com.arifwidayana.shared.data.network.datasource.UserDatasource
import com.arifwidayana.shared.data.network.datasource.UserDatasourceImpl
import com.arifwidayana.shared.data.network.service.UserService
import com.arifwidayana.shared.data.repository.*
import com.arifwidayana.shared.domain.*
import com.arifwidayana.shared.utils.Constant.DB_NAME
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

object SharedModule : BaseModule {
    private val local = module {
        single { UserPreferenceFactory(androidContext()).create() }
        single {
            Room.databaseBuilder(
                androidContext(),
                PrelovedDatabase::class.java,
                DB_NAME
            ).fallbackToDestructiveMigration().build()
        }
        single { get<PrelovedDatabase>().searchHistoryDao() }
    }

    private val network = module {
        single { ChuckerInterceptor.Builder(androidContext()).build() }
        single { NetworkClient(get(), get()) }
        single<UserService> { get<NetworkClient>().create() }
    }

    private val datasource = module {
        single<UserPreferenceDatasource> { UserPreferenceDatasourceImpl(get()) }
        single<SearchHistoryDatasource> { SearchHistoryDatasourceImpl(get()) }
        single<UserDatasource> { UserDatasourceImpl(get()) }
    }

    private val repository = module {
        single<UserPreferenceRepository> { UserPreferenceRepositoryImpl(get()) }
        single<SearchHistoryRepository> { SearchHistoryRepositoryImpl(get()) }
        single<UserRepository> { UserRepositoryImpl(get()) }
    }

    private val useCase = module {
        single { DeleteUserTokenUseCase(get(), Dispatchers.IO) }
        single { SetUserTokenUseCase(get(), Dispatchers.IO) }
        single { GetUserTokenUseCase(get(), Dispatchers.IO) }
        single { UserUseCase(get(), Dispatchers.IO) }
        single { ValidateUserTokenUseCase(get(), Dispatchers.IO) }
    }

    private val common = module {
        single { Gson() }
    }

    override fun getModules(): List<Module> =
        listOf(local, network, datasource, repository, useCase, common)
}