package com.arifwidayana.sale.presentation.ui.history

import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.shared.data.network.model.response.sale.history.HistoryParamResponse
import kotlinx.coroutines.flow.StateFlow

interface HistoryContract {
    val getHistoryResult: StateFlow<ViewResource<List<HistoryParamResponse>>>
    fun getHistory()
}