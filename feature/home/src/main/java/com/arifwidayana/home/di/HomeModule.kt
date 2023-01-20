package com.arifwidayana.home.di

import com.arifwidayana.core.base.FeatureModule
import com.arifwidayana.home.data.network.datasource.HomeDatasource
import com.arifwidayana.home.data.network.datasource.HomeDatasourceImpl
import com.arifwidayana.home.data.network.repository.HomeRepository
import com.arifwidayana.home.data.network.repository.HomeRepositoryImpl
import com.arifwidayana.home.data.network.service.HomeService
import com.arifwidayana.home.domain.CategoryProductUseCase
import com.arifwidayana.home.domain.HomeUseCase
import com.arifwidayana.home.presentation.ui.home.HomeViewModel
import com.arifwidayana.shared.data.network.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object HomeModule : FeatureModule {
    override val dataSources: Module = module {
        single<HomeDatasource> { HomeDatasourceImpl(get()) }
    }

    override val repositories: Module = module {
        single<HomeRepository> { HomeRepositoryImpl(get()) }
    }

    override val useCases: Module = module {
        single { HomeUseCase(get(), Dispatchers.IO) }
        single { CategoryProductUseCase(get(), Dispatchers.IO) }
    }

    override val networks: Module = module {
        single<HomeService> { get<NetworkClient>().create() }
    }

    override val viewModels: Module = module {
        viewModelOf(::HomeViewModel)
    }

    override fun getModules(): List<Module> =
        listOf(dataSources, repositories, useCases, networks, viewModels)
}