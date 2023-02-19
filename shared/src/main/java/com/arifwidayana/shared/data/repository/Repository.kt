package com.arifwidayana.shared.data.repository

import com.arifwidayana.core.base.BaseDefaultResponse
import com.arifwidayana.core.base.BaseRepository
import com.google.gson.Gson
import com.google.gson.JsonParseException
import okhttp3.ResponseBody
import org.koin.java.KoinJavaComponent

open class Repository : BaseRepository() {
    private val gson: Gson by KoinJavaComponent.inject(Gson::class.java)

    override fun <T> getErrorMessageFromApi(response: T): String {
        val responseBody = response as ResponseBody
        return try {
            val body = gson.fromJson(responseBody.string(), BaseDefaultResponse::class.java)
            body.message ?: "Error Api"
        } catch (e: JsonParseException) {
            "Error Api"
        }
    }
}