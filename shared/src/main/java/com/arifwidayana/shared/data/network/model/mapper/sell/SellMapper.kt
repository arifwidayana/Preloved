package com.arifwidayana.shared.data.network.model.mapper.sell

import com.arifwidayana.shared.data.network.model.request.sell.SellParamRequest
import com.arifwidayana.shared.utils.mapper.DataObjectMapper
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody

object SellMapper : DataObjectMapper<RequestBody, SellParamRequest> {
    override fun toDataObject(viewParam: SellParamRequest?): RequestBody =
        requestPartBody(viewParam)

    private fun requestPartBody(viewParam: SellParamRequest?): RequestBody {
        val requestBuilder = MultipartBody.Builder()
        viewParam?.let {
            it.image?.let { file ->
                requestBuilder.setType(MultipartBody.FORM)
                    .addFormDataPart("name", it.name)
                    .addFormDataPart("description", it.description)
                    .addFormDataPart("base_price", it.basePrice.toString())
                    .addFormDataPart("category_ids", it.categoryId.map { map -> map.id }.toString())
                    .addFormDataPart("location", it.location)
                    .addFormDataPart("image", file.name, file.asRequestBody("image/jpg".toMediaType()))
            }
        }
        return requestBuilder.build()
    }
}