package com.example.paypay.viewmodels

import com.example.paypay.models.ConvertedCurrencyRate

sealed class LatestCurrencyUiState {
    data class Success(val convertedCurrencyRates: List<ConvertedCurrencyRate>): LatestCurrencyUiState()
    data class Error(val exception: Throwable): LatestCurrencyUiState()
    data object Loading: LatestCurrencyUiState()
}