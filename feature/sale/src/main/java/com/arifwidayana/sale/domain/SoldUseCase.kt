package com.arifwidayana.sale.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.sale.data.repository.SaleRepository
import com.arifwidayana.shared.data.network.model.mapper.sale.SaleProductSoldMapper
import com.arifwidayana.shared.data.network.model.response.sale.sold.SaleProductSoldParamResponse
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SoldUseCase(
    private val saleRepository: SaleRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, List<SaleProductSoldParamResponse>>(coroutineDispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<List<SaleProductSoldParamResponse>>> =
        flow {
            emit(ViewResource.Loading())
            saleRepository.getSellerProduct().collect {
                it.suspendSource(
                    doOnSuccess = { source ->
                        emit(ViewResource.Success(SaleProductSoldMapper.toViewParam(source.payload?.filter { data -> data.status == Constant.SOLD })))
                    },
                    doOnError = { error ->
                        emit(ViewResource.Error(error.exception))
                    }
                )
            }
        }
}