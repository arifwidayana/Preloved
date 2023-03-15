package com.arifwidayana.home.data.network.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arifwidayana.home.data.network.repository.HomeRepository
import com.arifwidayana.shared.data.network.model.mapper.home.BuyerProductListMapper
import com.arifwidayana.shared.data.network.model.request.home.ProductParamRequest
import com.arifwidayana.shared.data.network.model.request.home.SearchProductParamRequest
import com.arifwidayana.shared.data.network.model.response.home.product.BuyerProductParamResponse
import com.arifwidayana.shared.utils.Constant.DEFAULT_INDEX_PAGE
import com.arifwidayana.shared.utils.Constant.DEFAULT_INDEX_PER_PAGE
import retrofit2.HttpException
import java.io.IOException

typealias BuyerProduct = BuyerProductParamResponse

class PagingDatasource(
    private val homeRepository: HomeRepository,
    private val searchProductParamRequest: SearchProductParamRequest
) : PagingSource<Int, BuyerProduct>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BuyerProduct> {
        val result: List<BuyerProductParamResponse>
        return try {
            val pageIndex = params.key ?: DEFAULT_INDEX_PAGE
            val response = homeRepository.showProduct(
                ProductParamRequest(
                    page = pageIndex,
                    perPage = DEFAULT_INDEX_PER_PAGE,
                    categoryId = searchProductParamRequest.categoryId,
                    search = searchProductParamRequest.searchProduct
                )
            )
            result = BuyerProductListMapper.toViewParam(response)
            LoadResult.Page(
                data = result,
                prevKey = if (pageIndex == DEFAULT_INDEX_PAGE) null else pageIndex - 1,
                nextKey = if (result.isEmpty()) null else pageIndex + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BuyerProduct>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}