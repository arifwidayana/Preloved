package com.arifwidayana.register.di

import com.arifwidayana.core.base.FeatureModule
import com.arifwidayana.register.data.datasource.RegisterDatasource
import com.arifwidayana.register.data.datasource.RegisterDatasourceImpl
import com.arifwidayana.register.data.repository.RegisterRepository
import com.arifwidayana.register.data.repository.RegisterRepositoryImpl
import com.arifwidayana.register.data.service.RegisterService
import com.arifwidayana.register.domain.RegisterFieldValidationUseCase
import com.arifwidayana.register.domain.RegisterUseCase
import com.arifwidayana.register.presentation.ui.RegisterViewModel
import com.arifwidayana.shared.data.network.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object RegisterModule : FeatureModule {
    override val dataSources: Module = module {
        single<RegisterDatasource> { RegisterDatasourceImpl(get()) }
    }

    override val repositories: Module = module {
        single<RegisterRepository> { RegisterRepositoryImpl(get()) }
    }

    override val useCases: Module = module {
        single { RegisterFieldValidationUseCase(Dispatchers.IO) }
        single { RegisterUseCase(get(), get(), Dispatchers.IO) }
    }

    override val networks: Module = module {
        single<RegisterService> { get<NetworkClient>().create() }
    }

    override val viewModels: Module = module {
        viewModelOf(::RegisterViewModel)
    }

    override fun getModules(): List<Module> =
        listOf(dataSources, repositories, useCases, networks, viewModels)
}