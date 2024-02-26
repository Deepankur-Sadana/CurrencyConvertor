package com.example.paypay.repository

import com.example.paypay.api.CurrencyApi
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * define reverse relationShip by your self
 */
class RemoteRepository @Inject constructor(
    private val remoteApi : CurrencyApi
) {

    private val currencyList = MutableStateFlow<List<String>>(emptyList())
    suspend fun getCurrencyList() {
        val response = remoteApi.getCurrencyList()
        if (response.isSuccessful && response.body() != null) {

        } else {

        }
    }





}