package com.example.paypay.api

import retrofit2.Response
import retrofit2.http.GET

interface CurrencyApi {

//    .url("https://openexchangerates.org/api/currencies.json?prettyprint=false&show_alternative=false&show_inactive=false&app_id=f0223b66dbd7460fb41437707310cdbd")

    @GET("/currencies.json")
    suspend fun getCurrency() : Response<List<CurrencyListItem>>

    @GET("/latest.json")
    suspend fun getLatestExchangeRates() : Response<LatestExchangeRateResponse>
}

/**
 * val client = OkHttpClient()
 *
 * val request = Request.Builder()
 *   .url("https://openexchangerates.org/api/latest.json?app_id=f0223b66dbd7460fb41437707310cdbd&base=INT&symbols=INR%2CEUR%2CAED%2CBAM%2CBHD&prettyprint=false&show_alternative=false")
 *   .get()
 *   .addHeader("accept", "application/json")
 *   .build()
 *
 * val response = client.newCall(request).execute()
 */