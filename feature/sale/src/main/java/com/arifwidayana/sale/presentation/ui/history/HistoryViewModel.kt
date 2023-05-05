package com.arifwidayana.sale.presentation.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.core.wrapper.ViewResource
import com.arifwidayana.sale.domain.HistoryUseCase
import com.arifwidayana.shared.data.network.model.response.sale.history.SaleHistoryParamResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val historyUseCase: HistoryUseCase
) : HistoryContract, ViewModel() {
    private val _getHistoryResult = MutableStateFlow<ViewResource<List<SaleHistoryParamResponse>>>(ViewResource.Empty())
    override val getHistoryResult: StateFlow<ViewResource<List<SaleHistoryParamResponse>>>
        get() = _getHistoryResult

    override fun getHistory() {
        viewModelScope.launch {
            historyUseCase().collect {
                _getHistoryResult.value = it
            }
        }
    }
}