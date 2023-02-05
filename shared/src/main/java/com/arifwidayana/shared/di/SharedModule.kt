package com.arifwidayana.shared.di

import androidx.room.Room
import com.arifwidayana.core.base.BaseModule
import com.arifwidayana.shared.data.local.PrelovedDatabase
import com.arifwidayana.shared.data.local.datasource.*
import com.arifwidayana.shared.data.network.NetworkClient
import com.arifwidayana.shared.data.repository.SearchHistoryRepository
import com.arifwidayana.shared.data.repository.SearchHistoryRepositoryImpl
import com.arifwidayana.shared.data.repository.UserPreferenceRepository
import com.arifwidayana.shared.data.repository.UserPreferenceRepositoryImpl
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
    }

    private val datasource = module {
        single<UserPreferenceDatasource> { UserPreferenceDatasourceImpl(get()) }
        single<SearchHistoryDatasource> { SearchHistoryDatasourceImpl(get()) }
    }

    private val repository = module {
        single<UserPreferenceRepository> { UserPreferenceRepositoryImpl(get()) }
        single<SearchHistoryRepository> { SearchHistoryRepositoryImpl(get()) }
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