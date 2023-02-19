package com.arifwidayana.bid.domain.product

import android.content.Context
import com.arifwidayana.bid.data.network.repository.BidRepository
import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.request.bid.product.WishlistProductRequest
import com.arifwidayana.shared.utils.ext.suspendSource
import com.arifwidayana.style.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WishlistProductUseCase(
    private val context: Context,
    private val bidRepository: BidRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Int, Boolean>(coroutineDispatcher) {
    override suspend fun execute(param: Int?): Flow<ViewResource<Boolean>> = flow {
        emit(ViewResource.Loading())
        param?.let {
            bidRepository.wishlistProduct().collect { result ->
                result.suspendSource(
                    doOnSuccess = { source ->
                        if (source.payload?.any { data -> data.productId == it } != true) {
                            bidRepository.postWishlistProduct(
                                WishlistProductRequest(productId = it)
                            ).collect { result ->
                                result.suspendSource(
                                    doOnSuccess = {
                                        emit(ViewResource.Success(true, context.getString(R.string.message_add_wishlist)))
                                    },
                                    doOnError = { error ->
                                        emit(ViewResource.Error(error.exception))
                                    }
                                )
                            }
                        } else {
                            source.payload?.firstOrNull { data -> data.productId == it }?.id?.let { res ->
                                bidRepository.deleteWishlistProduct(res).collect { result ->
                                    result.suspendSource(
                                        doOnSuccess = {
                                            emit(ViewResource.Success(false, context.getString(R.string.message_delete_wishlist)))
                                        },
                                        doOnError = { error ->
                                            emit(ViewResource.Error(error.exception))
                                        }
                                    )
                                }
                            }
                        }
                    },
                    doOnError = { error ->
                        emit(ViewResource.Error(error.exception))
                    }
                )
            }
        }
    }
}