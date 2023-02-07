package com.arifwidayana.home.di

import com.arifwidayana.core.base.FeatureModule
import com.arifwidayana.home.data.network.datasource.HomeDatasource
import com.arifwidayana.home.data.network.datasource.HomeDatasourceImpl
import com.arifwidayana.home.data.network.repository.HomeRepository
import com.arifwidayana.home.data.network.repository.HomeRepositoryImpl
import com.arifwidayana.home.data.network.service.HomeService
import com.arifwidayana.home.domain.home.BannerUseCase
import com.arifwidayana.home.domain.home.CategoryProductUseCase
import com.arifwidayana.home.domain.home.ProductUseCase
import com.arifwidayana.home.domain.search.GetFindSearchHistoryUseCase
import com.arifwidayana.home.domain.search.GetSearchHistoryUseCase
import com.arifwidayana.home.domain.search.PostSearchHistoryUseCase
import com.arifwidayana.home.presentation.ui.home.HomeViewModel
import com.arifwidayana.home.presentation.ui.search.SearchViewModel
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
        single { BannerUseCase(get(), Dispatchers.IO) }
        single { CategoryProductUseCase(get(), Dispatchers.IO) }
        single { ProductUseCase(get()) }
        single { PostSearchHistoryUseCase(get(), Dispatchers.IO) }
        single { GetSearchHistoryUseCase(get(), Dispatchers.IO) }
        single { GetFindSearchHistoryUseCase(get(), Dispatchers.IO) }
    }

    override val networks: Module = module {
        single<HomeService> { get<NetworkClient>().create() }
    }

    override val viewModels: Module = module {
        viewModelOf(::HomeViewModel)
        viewModelOf(::SearchViewModel)
    }

    override fun getModules(): List<Module> =
        listOf(dataSources, repositories, useCases, networks, viewModels)
}