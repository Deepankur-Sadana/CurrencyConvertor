package com.example.paypay.api

data class CurrencyRatesResponse(
    val disclaimer: String,
    val license: String,
    val timestamp: Long,
    val base: String,
    val rates: Map<String, Long>
)