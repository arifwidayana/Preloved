package com.arifwidayana.bid.domain.product

import com.arifwidayana.bid.data.network.repository.BidRepository
import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class ValidateWishlistProductUseCase(
    private val bidRepository: BidRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Int, Boolean>(coroutineDispatcher) {
    override suspend fun execute(param: Int?): Flow<ViewResource<Boolean>> = flow {
        emit(ViewResource.Loading())
        param?.let {
            bidRepository.wishlistProduct().first().suspendSource(
                doOnSuccess = { source ->
                    if (source.payload?.any { data -> data.productId == it } == true) {
                        emit(ViewResource.Success(true))
                    } else {
                        emit(ViewResource.Success(false))
                    }
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }
    }
}