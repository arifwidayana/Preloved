package com.arifwidayana.sell.di

import com.arifwidayana.core.base.FeatureModule
import com.arifwidayana.sell.data.datasource.SellDatasource
import com.arifwidayana.sell.data.datasource.SellDatasourceImpl
import com.arifwidayana.sell.data.repository.SellRepository
import com.arifwidayana.sell.data.repository.SellRepositoryImpl
import com.arifwidayana.sell.data.service.SellService
import com.arifwidayana.sell.domain.CategoryProductUseCase
import com.arifwidayana.sell.domain.CreateProductFieldValidationUseCase
import com.arifwidayana.sell.domain.CreateProductUseCase
import com.arifwidayana.sell.presentation.ui.category.AddCategoryViewModel
import com.arifwidayana.sell.presentation.ui.sell.SellViewModel
import com.arifwidayana.shared.data.network.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object SellModule : FeatureModule {
    override val dataSources: Module = module {
        single<SellDatasource> { SellDatasourceImpl(get()) }
    }

    override val repositories: Module = module {
        single<SellRepository> { SellRepositoryImpl(get()) }
    }

    override val useCases: Module = module {
        single { CategoryProductUseCase(get(), Dispatchers.IO) }
        single { CreateProductFieldValidationUseCase(Dispatchers.IO) }
        single { CreateProductUseCase(get(), get(), Dispatchers.IO) }
    }

    override val networks: Module = module {
        single<SellService> { get<NetworkClient>().create() }
    }

    override val viewModels: Module = module {
        viewModelOf(::SellViewModel)
        viewModelOf(::AddCategoryViewModel)
    }

    override fun getModules(): List<Module> =
        listOf(dataSources, repositories, useCases, networks, viewModels)
}