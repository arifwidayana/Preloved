package com.arifwidayana.home.di

import com.arifwidayana.core.base.FeatureModule
import org.koin.core.module.Module
import org.koin.dsl.module

object HomeModule : FeatureModule {
    override val dataSources: Module = module {
    }

    override val repositories: Module = module {
    }

    override val useCases: Module = module {
    }

    override val networks: Module = module {
    }

    override val viewModels: Module = module {
    }

    override fun getModules(): List<Module> =
        listOf(dataSources, dataSources, useCases, networks, viewModels)
}