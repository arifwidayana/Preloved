package com.arifwidayana.sell.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.sell.data.repository.SellRepository
import com.arifwidayana.shared.data.network.model.mapper.home.CategoryProductMapper
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryParamResponse
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class CategoryProductUseCase(
    private val sellRepository: SellRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, List<CategoryParamResponse>>(coroutineDispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<List<CategoryParamResponse>>> = flow {
        emit(ViewResource.Loading())
        sellRepository.getCategoryProduct().first().suspendSource(
            doOnSuccess = {
                emit(ViewResource.Success(CategoryProductMapper.toViewParam(it.payload)))
            },
            doOnError = {
                emit(ViewResource.Error(it.exception))
            }
        )
    }
}