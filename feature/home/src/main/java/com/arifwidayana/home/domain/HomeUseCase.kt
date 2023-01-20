package com.arifwidayana.home.domain

import com.arifwidayana.home.data.network.repository.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher

class HomeUseCase(
    private val homeRepository: HomeRepository,
    coroutineDispatcher: CoroutineDispatcher
)