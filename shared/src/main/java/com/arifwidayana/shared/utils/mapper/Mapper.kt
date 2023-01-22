package com.arifwidayana.shared.utils.mapper

import androidx.paging.PagingData
import androidx.paging.map

interface Mapper<DTO, ViewParam>

interface ViewParamMapper<DTO, ViewParam> : Mapper<DTO, ViewParam> {
    fun toViewParam(dataObject: DTO?): ViewParam
}

interface DataObjectMapper<DTO, ViewParam> : Mapper<DTO, ViewParam> {
    fun toDataObject(viewParam: ViewParam?): DTO
}

interface DataMapper<DTO, ViewParam> : ViewParamMapper<DTO, ViewParam>, DataObjectMapper<DTO, ViewParam>

class ListMapper<DTO, ViewParam>(private val mapper: Mapper<DTO, ViewParam>) {
    fun toDataObjects(viewParams: List<ViewParam>?): List<DTO> {
        if (mapper !is DataObjectMapper) throw IllegalStateException("Needs mapper DataObjectMapper.kt")
        if (viewParams.isNullOrEmpty()) return listOf()
        return viewParams.map {
            mapper.toDataObject(it)
        }
    }

    fun toViewParams(dataObjects: List<DTO>?): List<ViewParam> {
        if (mapper !is ViewParamMapper) throw IllegalStateException("Needs mapper ViewParamMapper.kt")
        if (dataObjects.isNullOrEmpty()) return listOf()
        return dataObjects.map {
            mapper.toViewParam(it)
        }
    }
}

class PagingDataMapper<DTO : Any, ViewParam : Any>(private val mapper: Mapper<DTO, ViewParam>) {
    fun toDataObjects(viewParams: PagingData<ViewParam>?): PagingData<DTO> {
        if (mapper !is DataObjectMapper) throw IllegalStateException("Needs mapper DataObjectMapper.kt")
        if (viewParams.toString().isEmpty()) return PagingData.empty()
        return viewParams?.map {
            mapper.toDataObject(it)
        } as PagingData<DTO>
    }

    fun toViewParams(dataObjects: PagingData<DTO>?): PagingData<ViewParam> {
        if (mapper !is ViewParamMapper) throw IllegalStateException("Needs mapper ViewParamMapper.kt")
        if (dataObjects.toString().isEmpty()) return PagingData.empty()
        return dataObjects?.map {
            mapper.toViewParam(it)
        } as PagingData<ViewParam>
    }
}

class ArrayListMapper<DTO, ViewParam>(private val mapper: Mapper<DTO, ViewParam>) {
    fun toDataObjects(viewParams: ArrayList<ViewParam>?): ArrayList<DTO> {
        if (mapper !is DataObjectMapper) throw IllegalStateException("Needs mapper DataObjectMapper.kt")
        if (viewParams.isNullOrEmpty()) return arrayListOf()
        return viewParams.map {
            mapper.toDataObject(it)
        } as ArrayList<DTO>
    }

    fun toViewParams(dataObjects: ArrayList<DTO>?): ArrayList<ViewParam> {
        if (mapper !is ViewParamMapper) throw IllegalStateException("Needs mapper ViewParamMapper.kt")
        if (dataObjects.isNullOrEmpty()) return arrayListOf()
        return dataObjects.map {
            mapper.toViewParam(it)
        } as ArrayList<ViewParam>
    }
}