package com.arifwidayana.home.data.network.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arifwidayana.home.data.network.service.HomeService
import com.arifwidayana.shared.data.network.model.request.home.CategoryParamRequest
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductResponse
import com.arifwidayana.shared.utils.Constant.DEFAULT_INDEX_PAGE
import com.arifwidayana.shared.utils.Constant.DEFAULT_INDEX_PER_PAGE
import retrofit2.HttpException
import java.io.IOException

typealias BuyerProduct = BuyerProductResponse

class PagingDatasource(
    private val homeService: HomeService,
    private val categoryParamRequest: CategoryParamRequest
) : PagingSource<Int, BuyerProduct>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BuyerProduct> {
        return try {
            val pageIndex = params.key ?: DEFAULT_INDEX_PAGE
            val response = if (categoryParamRequest.categoryId != 0) {
                homeService.showProduct(
                    page = pageIndex,
                    perPage = DEFAULT_INDEX_PER_PAGE,
                    categoryId = categoryParamRequest.categoryId
                )
            } else {
                homeService.showAllProduct(
                    page = pageIndex,
                    perPage = DEFAULT_INDEX_PER_PAGE
                )
            }
            LoadResult.Page(
                data = response,
                prevKey = if (pageIndex == DEFAULT_INDEX_PAGE) null else pageIndex - 1,
                nextKey = if (response.isEmpty()) null else pageIndex + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BuyerProduct> {
//        return try {
//            val pageIndex = params.key ?: DEFAULT_INDEX_PAGE
//            Log.d("TAG", "load: TRIGGER")
//            val response = homeRepository.showProduct(
//                ProductParamRequest(
//                    page = pageIndex,
//                    perPage = DEFAULT_INDEX_PER_PAGE,
//                    categoryId = categoryParamRequest.categoryId
//                )
//            )
//            LoadResult.Page(
//                data = response,
//                prevKey = if (pageIndex == DEFAULT_INDEX_PAGE) null else pageIndex - 1,
//                nextKey = if (response.isEmpty()) null else pageIndex + 1
//            )
//        } catch (exception: IOException) {
//            return LoadResult.Error(exception)
//        } catch (exception: HttpException) {
//            return LoadResult.Error(exception)
//        }
//    }

    override fun getRefreshKey(state: PagingState<Int, BuyerProduct>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}