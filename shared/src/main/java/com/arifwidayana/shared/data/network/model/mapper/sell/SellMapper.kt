package com.arifwidayana.shared.data.network.model.mapper.sell

import com.arifwidayana.shared.data.network.model.request.sell.SellParamRequest
import com.arifwidayana.shared.data.network.model.request.sell.SellRequest
import com.arifwidayana.shared.utils.mapper.DataObjectMapper
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

object SellMapper : DataObjectMapper<SellRequest, SellParamRequest> {
    override fun toDataObject(viewParam: SellParamRequest?): SellRequest =
        SellRequest(
            name = viewParam?.name,
            description = viewParam?.description,
            basePrice = viewParam?.basePrice,
            categoryId = viewParam?.categoryId,
            location = viewParam?.location,
            image = viewParam?.image?.let { requestPartBody(it) }
        )

    private fun requestPartBody(file: File): RequestBody {
        val requestBody = file.asRequestBody("image/*".toMediaType())
        return MultipartBody.Builder()
            .addFormDataPart("image", file.name, requestBody)
            .build()
    }
}