package com.arifwidayana.sell.domain

import com.arifwidayana.core.base.BaseUseCase
import com.arifwidayana.core.utils.FieldErrorException
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.request.sell.SellParamRequest
import com.arifwidayana.shared.utils.Constant
import com.arifwidayana.style.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File

typealias FieldCreateProductResult = List<Pair<Int, Int>>

class CreateProductFieldValidationUseCase(
    coroutineDispatcher: CoroutineDispatcher
) : BaseUseCase<SellParamRequest, FieldCreateProductResult>(coroutineDispatcher) {
    override suspend fun execute(param: SellParamRequest?): Flow<ViewResource<FieldCreateProductResult>> = flow {
        param?.let { res ->
            val result = mutableListOf<Pair<Int, Int>>()
            isNameValid(res.name)?.let {
                result.add(it)
            }
            isDescriptionValid(res.description)?.let {
                result.add(it)
            }
            isPriceValid(res.basePrice)?.let {
                result.add(it)
            }
            isCategoryValid(res.categoryId.size)?.let {
                result.add(it)
            }
            isImageValid(res.image)?.let {
                result.add(it)
            }
            when {
                result.isEmpty() -> emit(ViewResource.Success(result))
                else -> emit(ViewResource.Error(FieldErrorException(result)))
            }
        } ?: throw IllegalStateException("Param Required")
    }

    private fun isNameValid(name: String): Pair<Int, Int>? {
        return when {
            name.isEmpty() -> Pair(Constant.SELL_NAME_FIELD, R.string.error_field_sell_name_empty)
            else -> null
        }
    }

    private fun isDescriptionValid(description: String): Pair<Int, Int>? {
        return when {
            description.isEmpty() -> Pair(Constant.SELL_DESCRIPTION_FIELD, R.string.error_field_sell_description_empty)
            else -> null
        }
    }

    private fun isPriceValid(price: Int): Pair<Int, Int>? {
        return when (price) {
            0 -> Pair(Constant.SELL_PRICE_FIELD, R.string.error_field_sell_price_empty)
            else -> null
        }
    }

    private fun isCategoryValid(category: Int): Pair<Int, Int>? {
        return when (category) {
            0 -> Pair(Constant.SELL_CATEGORY_FIELD, R.string.error_field_sell_category_empty)
            else -> null
        }
    }

    private fun isImageValid(image: File?): Pair<Int, Int>? {
        return when (image) {
            null -> Pair(Constant.SELL_IMAGE_FIELD, R.string.error_field_sell_image_empty)
            else -> null
        }
    }
}