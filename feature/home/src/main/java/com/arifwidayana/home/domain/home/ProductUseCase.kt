package com.arifwidayana.home.domain.home

import androidx.paging.PagingData
import com.arifwidayana.home.data.network.repository.HomeRepository
import com.arifwidayana.shared.data.network.model.mapper.home.BuyerProductMapper
import com.arifwidayana.shared.data.network.model.request.home.CategoryParamRequest
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductParamResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias ProductDataResource = PagingData<BuyerProductParamResponse>

class ProductUseCase(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(param: Int): Flow<ProductDataResource> = flow {
        homeRepository.showProduct(CategoryParamRequest(param)).collect {
            emit(BuyerProductMapper.toViewParam(it))
        }
    }
//    override suspend fun execute(param: Int?): Flow<ViewResource<ProductDataResource>> =
//        flow {
//            param?.let { request ->
//                emit(ViewResource.Success(
//                    Pager(
//                        config = PagingConfig(
//                            pageSize = Constant.NETWORK_PAGE_SIZE,
//                            enablePlaceholders = false
//                        ),
//                        pagingSourceFactory = {
//                            PagingDatasource(
//                                homeRepository = homeRepository,
//                                categoryParamRequest = CategoryParamRequest(request)
//                            )
//                        }
//                    )
//                ))
// //                homeRepository.showProduct(request).collect {
// //                    it.suspendSource(
// //                        doOnSuccess = { source ->
// //                            emit(ViewResource.Success(BuyerProductMapper.toViewParam(source.payload)))
// //                        },
// //                        doOnError = { error ->
// //                            emit(ViewResource.Error(error.exception))
// //                        }
// //                    )
// //                }
//            }
//        }
}