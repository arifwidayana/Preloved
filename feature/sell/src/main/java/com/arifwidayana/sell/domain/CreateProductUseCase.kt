package com.arifwidayana.sell.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.sell.data.repository.SellRepository
import com.arifwidayana.shared.data.network.model.mapper.sell.SellMapper
import com.arifwidayana.shared.data.network.model.request.sell.SellParamRequest
import com.arifwidayana.shared.data.network.model.response.sell.SellResponse
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class CreateProductUseCase(
    private val sellRepository: SellRepository,
    private val fieldValidation: CreateProductFieldValidationUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<SellParamRequest, SellResponse>(coroutineDispatcher) {
    override suspend fun execute(param: SellParamRequest?): Flow<ViewResource<SellResponse>> = flow {
        emit(ViewResource.Loading())
        param?.let { p ->
            fieldValidation(p).first().suspendSource(
                doOnSuccess = {
                    sellRepository.createProduct(
                        SellMapper.toDataObject(p)
                    ).first().suspendSource(
                        doOnSuccess = { source ->
                            source.payload?.let { res ->
                                emit(ViewResource.Success(res))
                            }
                        },
                        doOnError = { error ->
                            emit(ViewResource.Error(error.exception))
                        }
                    )
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
    }
}