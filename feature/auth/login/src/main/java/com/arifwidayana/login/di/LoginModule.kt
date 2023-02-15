package com.arifwidayana.login.di

import com.arifwidayana.core.base.FeatureModule
import com.arifwidayana.login.data.network.datasource.LoginDatasource
import com.arifwidayana.login.data.network.datasource.LoginDatasourceImpl
import com.arifwidayana.login.data.network.repository.LoginRepository
import com.arifwidayana.login.data.network.repository.LoginRepositoryImpl
import com.arifwidayana.login.data.network.service.LoginService
import com.arifwidayana.login.domain.LoginFieldValidationUseCase
import com.arifwidayana.login.domain.LoginUseCase
import com.arifwidayana.login.presentation.ui.login.LoginViewModel
import com.arifwidayana.shared.data.network.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object LoginModule : FeatureModule {
    override val dataSources: Module = module {
        single<LoginDatasource> { LoginDatasourceImpl(get()) }
    }

    override val repositories: Module = module {
        single<LoginRepository> { LoginRepositoryImpl(get()) }
    }

    override val useCases: Module = module {
        single { LoginFieldValidationUseCase(Dispatchers.IO) }
        single { LoginUseCase(get(), get(), get(), Dispatchers.IO) }
    }

    override val networks: Module = module {
        single<LoginService> { get<NetworkClient>().create() }
    }

    override val viewModels: Module = module {
        viewModelOf(::LoginViewModel)
    }

    override fun getModules(): List<Module> =
        listOf(dataSources, repositories, useCases, networks, viewModels)
}