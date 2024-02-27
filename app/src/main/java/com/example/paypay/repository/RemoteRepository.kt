package com.example.paypay.repository

import com.example.paypay.api.CurrencyApi
import kotlinx.coroutines.flow.MutableStateFlow
import java.lang.StringBuilder
import javax.inject.Inject

interface IRemoteRepository {
    suspend fun getExchangeRatesList(): Map<String, Double>
    suspend fun refreshExchangeRatesList(
        currencyList: ArrayList<String>
    ): Map<String, Double>
}

/**
 * define reverse relationShip by your self
 */
class RemoteRepository @Inject constructor(
    private val remoteApi: CurrencyApi
) : IRemoteRepository {

    private val currencyList = MutableStateFlow<List<String>>(emptyList())
    override suspend fun getExchangeRatesList(): Map<String, Double> {
        val response = remoteApi.getCurrencyList()
        if (response.isSuccessful && response.body() != null) {
            val symbols = StringBuilder()
            //"AED,INR,USD,EUR"
            response.body()!!.entries.forEach { symbols.append("${it},") }
            val exchangeRateResponse = remoteApi
                .getLatestExchangeRates(symbols.toString())
                .body()

            return exchangeRateResponse!!.rates

        } else {
            TODO("return error here")
            return emptyMap()
        }

    }

    override suspend fun refreshExchangeRatesList(
        currencyList: ArrayList<String>
    ): Map<String, Double> {
        val symbols = StringBuilder()
        //"AED,INR,USD,EUR"
        currencyList.forEach { symbols.append("${it},") }

        val exchangeRateResponse = remoteApi
            .getLatestExchangeRates(symbols.toString())
            .body()
        return exchangeRateResponse!!.rates
    }




}