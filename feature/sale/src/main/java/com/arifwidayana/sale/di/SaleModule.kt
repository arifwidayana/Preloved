package com.arifwidayana.sale.di

import com.arifwidayana.core.base.FeatureModule
import com.arifwidayana.sale.data.datasource.SaleDatasource
import com.arifwidayana.sale.data.datasource.SaleDatasourceImpl
import com.arifwidayana.sale.data.repository.SaleRepository
import com.arifwidayana.sale.data.repository.SaleRepositoryImpl
import com.arifwidayana.sale.data.service.SaleService
import com.arifwidayana.sale.domain.HistoryUseCase
import com.arifwidayana.sale.domain.OrderUseCase
import com.arifwidayana.sale.domain.ProductOfferUseCase
import com.arifwidayana.sale.domain.ProductUseCase
import com.arifwidayana.sale.domain.SoldUseCase
import com.arifwidayana.sale.presentation.ui.bidder.BidderViewModel
import com.arifwidayana.sale.presentation.ui.history.HistoryViewModel
import com.arifwidayana.sale.presentation.ui.order.OrderViewModel
import com.arifwidayana.sale.presentation.ui.product.ProductViewModel
import com.arifwidayana.sale.presentation.ui.sale.SaleViewModel
import com.arifwidayana.sale.presentation.ui.sold.SoldViewModel
import com.arifwidayana.shared.data.network.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object SaleModule : FeatureModule {
    override val dataSources: Module = module {
        single<SaleDatasource> { SaleDatasourceImpl(get()) }
    }

    override val repositories: Module = module {
        single<SaleRepository> { SaleRepositoryImpl(get()) }
    }

    override val useCases: Module = module {
        single { HistoryUseCase(get(), Dispatchers.IO) }
        single { ProductUseCase(get(), Dispatchers.IO) }
        single { SoldUseCase(get(), Dispatchers.IO) }
        single { OrderUseCase(get(), Dispatchers.IO) }
        single { ProductOfferUseCase(get(), Dispatchers.IO) }
    }

    override val networks: Module = module {
        single<SaleService> { get<NetworkClient>().create() }
    }

    override val viewModels: Module = module {
        viewModelOf(::SaleViewModel)
        viewModelOf(::ProductViewModel)
        viewModelOf(::OrderViewModel)
        viewModelOf(::SoldViewModel)
        viewModelOf(::HistoryViewModel)
        viewModelOf(::BidderViewModel)
    }

    override fun getModules(): List<Module> =
        listOf(dataSources, repositories, useCases, networks, viewModels)
}