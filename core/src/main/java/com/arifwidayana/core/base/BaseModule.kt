package com.arifwidayana.core.base

import org.koin.core.module.Module

interface BaseModule {
    fun getModules(): List<Module>
}

interface FeatureModule: BaseModule {
    val dataSources: Module
    val repositories: Module
    val useCases: Module
    val networks: Module
    val viewModels: Module
}