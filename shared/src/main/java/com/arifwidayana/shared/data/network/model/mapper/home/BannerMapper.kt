package com.arifwidayana.shared.data.network.model.mapper.home

import com.arifwidayana.shared.data.network.model.response.home.banner.BannerParamResponse
import com.arifwidayana.shared.data.network.model.response.home.banner.BannerResponse
import com.arifwidayana.shared.utils.mapper.ListMapper
import com.arifwidayana.shared.utils.mapper.ViewParamMapper

typealias BannerMap = BannerResponse
typealias BannerParamMap = BannerParamResponse

object BannerMapper : ViewParamMapper<List<BannerMap>, List<BannerParamMap>> {
    override fun toViewParam(dataObject: List<BannerMap>?): List<BannerParamMap> =
        ListMapper(BannerListMapper).toViewParams(dataObject)
}

object BannerListMapper : ViewParamMapper<BannerMap, BannerParamMap> {
    override fun toViewParam(dataObject: BannerMap?): BannerParamMap =
        BannerParamMap(
            id = dataObject?.id ?: 0,
            name = dataObject?.name.orEmpty(),
            imageUrl = dataObject?.imageUrl.orEmpty(),
            createdAt = dataObject?.createdAt.orEmpty(),
            updatedAt = dataObject?.updatedAt.orEmpty()
        )
}