package com.arifwidayana.home.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.home.data.network.repository.HomeRepository
import com.arifwidayana.shared.data.network.model.mapper.home.CategoryListProductMapper
import com.arifwidayana.shared.data.network.model.response.home.category.CategoryParamResponse
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias CategoryDataResource = List<CategoryParamResponse>

class CategoryProductUseCase(
    private val homeRepository: HomeRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, CategoryDataResource>(coroutineDispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<CategoryDataResource>> = flow {
        emit(ViewResource.Loading())
        homeRepository.categoryProduct().collect { source ->
            source.suspendSource(
                doOnSuccess = {
                    emit(ViewResource.Success(CategoryListProductMapper.toViewParam(it.payload)))
                },
                doOnError = {
                    emit(ViewResource.Error(it.exception))
                }
            )
        }
    }
}