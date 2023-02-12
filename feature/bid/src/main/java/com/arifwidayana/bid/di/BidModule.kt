package com.arifwidayana.bid.di

import com.arifwidayana.bid.data.network.datasource.BidDatasource
import com.arifwidayana.bid.data.network.datasource.BidDatasourceImpl
import com.arifwidayana.bid.data.network.repository.BidRepository
import com.arifwidayana.bid.data.network.repository.BidRepositoryImpl
import com.arifwidayana.bid.data.network.service.BidService
import com.arifwidayana.bid.domain.product.DetailProductUseCase
import com.arifwidayana.bid.domain.product.ValidateWishlistProductUseCase
import com.arifwidayana.bid.domain.product.WishlistProductUseCase
import com.arifwidayana.bid.presentation.ui.product.DetailProductViewModel
import com.arifwidayana.core.base.FeatureModule
import com.arifwidayana.shared.data.network.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object BidModule : FeatureModule {
    override val dataSources: Module = module {
        single<BidDatasource> { BidDatasourceImpl(get()) }
    }

    override val repositories: Module = module {
        single<BidRepository> { BidRepositoryImpl(get()) }
    }

    override val useCases: Module = module {
        single { DetailProductUseCase(get(), Dispatchers.IO) }
        single { WishlistProductUseCase(androidContext(), get(), Dispatchers.IO) }
        single { ValidateWishlistProductUseCase(get(), Dispatchers.IO) }
    }

    override val networks: Module = module {
        single<BidService> { get<NetworkClient>().create() }
    }

    override val viewModels: Module = module {
        viewModelOf(::DetailProductViewModel)
    }

    override fun getModules(): List<Module> =
        listOf(dataSources, repositories, useCases, networks, viewModels)
}