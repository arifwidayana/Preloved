package com.arifwidayana.account.domain.wishlist

import com.arifwidayana.account.data.repository.AccountRepository
import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.mapper.account.wishlist.WishlistMapper
import com.arifwidayana.shared.data.network.model.response.account.wishlist.WishlistAccountParamResponse
import com.arifwidayana.shared.utils.ext.suspendSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

typealias WishlistParamDataResource = List<WishlistAccountParamResponse>

class WishlistUseCase(
    private val accountRepository: AccountRepository,
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, WishlistParamDataResource>(coroutineDispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<WishlistParamDataResource>> = flow {
        emit(ViewResource.Loading())
        accountRepository.getWishlist().first().suspendSource(
            doOnSuccess = { source ->
                emit(ViewResource.Success(WishlistMapper.toViewParam(source.payload)))
            },
            doOnError = { error ->
                emit(ViewResource.Error(error.exception))
            }
        )
    }
}