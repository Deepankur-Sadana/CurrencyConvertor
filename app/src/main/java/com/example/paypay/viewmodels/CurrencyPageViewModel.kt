package com.example.paypay.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paypay.IDataManager
import com.example.paypay.calculators.ICurrencyInterConverter
import com.example.paypay.data.IDataRefresher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyPageViewModel @Inject constructor(
    private val dataManager: IDataManager,
    private val dataRefresher: IDataRefresher,
    private val currencyInterConverter: ICurrencyInterConverter
) : ViewModel() {

    companion object {
        const val DEFAULT_CURRENCY = "USD"
        const val DEFAULT_AMOUNT = 1.0
    }

    var inputAmount = DEFAULT_AMOUNT
    var currency = DEFAULT_CURRENCY
    fun updateInputAmount(value: Double) {

    }

    private var staticMapOfCurrencies = MutableStateFlow(LatestCurrencyUiState.Success(emptyList()))

    private val _uiState = MutableStateFlow(LatestCurrencyUiState.Success(emptyList()))

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<LatestCurrencyUiState> = _uiState

    init {
        viewModelScope.launch {
            val data = dataManager.getData()
            currencyInterConverter.loadCurrencyList(data)
            staticMapOfCurrencies.value = LatestCurrencyUiState.Success(data)
            _uiState.value = LatestCurrencyUiState.Success(data)
        }
    }


}