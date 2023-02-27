package com.arifwidayana.account.di

import com.arifwidayana.account.data.datasource.AccountDatasource
import com.arifwidayana.account.data.datasource.AccountDatasourceImpl
import com.arifwidayana.account.data.repository.AccountRepository
import com.arifwidayana.account.data.repository.AccountRepositoryImpl
import com.arifwidayana.account.data.service.AccountService
import com.arifwidayana.account.domain.UserUseCase
import com.arifwidayana.account.presentation.ui.account.AccountViewModel
import com.arifwidayana.account.presentation.ui.order.OrderViewModel
import com.arifwidayana.account.presentation.ui.password.PasswordViewModel
import com.arifwidayana.account.presentation.ui.profile.ProfileViewModel
import com.arifwidayana.account.presentation.ui.wishlist.WishlistViewModel
import com.arifwidayana.core.base.FeatureModule
import com.arifwidayana.shared.data.network.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object AccountModule : FeatureModule {
    override val dataSources: Module = module {
        single<AccountDatasource> { AccountDatasourceImpl(get()) }
    }

    override val repositories: Module = module {
        single<AccountRepository> { AccountRepositoryImpl(get()) }
    }

    override val useCases: Module = module {
        single { UserUseCase(get(), Dispatchers.IO) }
    }

    override val networks: Module = module {
        single<AccountService> { get<NetworkClient>().create() }
    }

    override val viewModels: Module = module {
        viewModelOf(::AccountViewModel)
        viewModelOf(::ProfileViewModel)
        viewModelOf(::PasswordViewModel)
        viewModelOf(::OrderViewModel)
        viewModelOf(::WishlistViewModel)
    }

    override fun getModules(): List<Module> =
        listOf(dataSources, repositories, useCases, networks, viewModels)
}