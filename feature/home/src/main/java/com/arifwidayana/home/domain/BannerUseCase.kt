package com.arifwidayana.home.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.home.data.network.repository.HomeRepository
import com.arifwidayana.shared.data.network.model.mapper.home.BannerListMapper
import com.arifwidayana.shared.data.network.model.response.home.banner.BannerParamResponse
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias BannerDataResource = List<BannerParamResponse>

class BannerUseCase(
    private val homeRepository: HomeRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, BannerDataResource>(coroutineDispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<BannerDataResource>> = flow {
        emit(ViewResource.Loading())
        homeRepository.showBanner().collect { source ->
            source.suspendSource(
                doOnSuccess = {
                    emit(ViewResource.Success(BannerListMapper.toViewParam(it.payload)))
                },
                doOnError = {
                    emit(ViewResource.Error(it.exception))
                }
            )
        }
    }
}