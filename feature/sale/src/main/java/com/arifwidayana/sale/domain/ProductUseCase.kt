package com.arifwidayana.sale.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.sale.data.repository.SaleRepository
import com.arifwidayana.shared.data.network.model.mapper.sale.SaleProductMapper
import com.arifwidayana.shared.data.network.model.response.sale.product.SaleProductParamResponse
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductUseCase(
    private val saleRepository: SaleRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, List<SaleProductParamResponse>>(coroutineDispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<List<SaleProductParamResponse>>> = flow {
        emit(ViewResource.Loading())
        saleRepository.getSellerProduct().collect {
            it.suspendSource(
                doOnSuccess = { source ->
                    emit(ViewResource.Success(SaleProductMapper.toViewParam(source.payload?.filter { data -> data.status == Constant.AVAILABLE })))
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
    }
}