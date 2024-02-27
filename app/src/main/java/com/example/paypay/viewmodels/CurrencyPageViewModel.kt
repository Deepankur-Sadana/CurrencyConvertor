package com.example.paypay.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paypay.IDataManager
import com.example.paypay.calculators.ICurrencyInterConverter
import com.example.paypay.data.IDataRefresher
import com.example.paypay.models.ConvertedCurrencyRate
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

    private var inputAmount = DEFAULT_AMOUNT
    private var currency = DEFAULT_CURRENCY
    private lateinit var selectedCurrency: ConvertedCurrencyRate

    private var staticMapOfCurrencies = MutableStateFlow(LatestCurrencyUiState.Success(emptyList()))

    private val _uiState = MutableStateFlow(LatestCurrencyUiState.Success(emptyList()))

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<LatestCurrencyUiState> = _uiState

    companion object {
        const val DEFAULT_CURRENCY = "USD"
        const val DEFAULT_AMOUNT = 1.0
    }


    init {
        viewModelScope.launch {
            val data = dataManager.getData(dataRefresher.getDataRefreshListener())
            setDefaultCurrency(data)
            currencyInterConverter.loadCurrencyList(data)
            staticMapOfCurrencies.value = LatestCurrencyUiState.Success(data)
            _uiState.value = LatestCurrencyUiState.Success(data)
        }
    }

    fun updateInputAmount(value: String) {
        if (value.toDoubleOrNull() == null) return
        this.inputAmount = value.toDouble()
        reCalculateRates(selectedCurrency.currencySymbol)
    }

    private fun reCalculateRates(currency: String) {
        val computedList = currencyInterConverter.convertTo(currency, inputAmount)
        _uiState.value = LatestCurrencyUiState.Success(computedList)
    }

    fun updateInputCurrencyIndex(index: Int) {
        selectedCurrency = staticMapOfCurrencies.value.convertedCurrencyRates[index]
        reCalculateRates(selectedCurrency.currencySymbol)
    }


    private fun setDefaultCurrency(data: List<ConvertedCurrencyRate>) {
        for (i in data.indices) {
            if (data[i].currencySymbol == DEFAULT_CURRENCY) {
                selectedCurrency = data[i]
                break
            }
        }
    }
}