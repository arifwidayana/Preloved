package com.arifwidayana.notification.di

import com.arifwidayana.core.base.FeatureModule
import com.arifwidayana.notification.data.network.datasource.NotificationDatasource
import com.arifwidayana.notification.data.network.datasource.NotificationDatasourceImpl
import com.arifwidayana.notification.data.network.repository.NotificationRepository
import com.arifwidayana.notification.data.network.repository.NotificationRepositoryImpl
import com.arifwidayana.notification.data.network.service.NotificationService
import com.arifwidayana.notification.domain.NotificationUseCase
import com.arifwidayana.notification.domain.ReadNotificationUseCase
import com.arifwidayana.notification.presentation.ui.NotificationViewModel
import com.arifwidayana.shared.data.network.NetworkClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object NotificationModule : FeatureModule {
    override val dataSources: Module = module {
        single<NotificationDatasource> { NotificationDatasourceImpl(get()) }
    }

    override val repositories: Module = module {
        single<NotificationRepository> { NotificationRepositoryImpl(get()) }
    }

    override val useCases: Module = module {
        single { NotificationUseCase(get(), Dispatchers.IO) }
        single { ReadNotificationUseCase(get(), get(), Dispatchers.IO) }
    }

    override val networks: Module = module {
        single<NotificationService> { get<NetworkClient>().create() }
    }

    override val viewModels: Module = module {
        viewModelOf(::NotificationViewModel)
    }

    override fun getModules(): List<Module> =
        listOf(dataSources, repositories, useCases, networks, viewModels)
}