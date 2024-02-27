package com.example.paypay.api

data class LatestExchangeRateResponse(
    val timestamp: Long,
    val rates: Map<String, Double>
)