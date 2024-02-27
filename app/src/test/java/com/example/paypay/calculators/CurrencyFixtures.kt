package com.example.paypay.calculators

import com.example.paypay.api.LatestExchangeRateResponse
import com.example.paypay.db.CurrencyRate
import com.example.paypay.models.ConvertedCurrencyRate

object CurrencyFixtures {


    fun getCurrencyListForConvertor() : List<ConvertedCurrencyRate>{
        val res = ArrayList<ConvertedCurrencyRate>()
        res.add(ConvertedCurrencyRate("USD", 1.0))
        res.add(ConvertedCurrencyRate("AOA", 831.5))
        res.add(ConvertedCurrencyRate("ARS", 34.5))
        return res

    }
    fun getCurrencyMap(): HashMap<String, Double> {
        val map = HashMap<String, Double>()
        map["USD"] = 1.0
        map["AOA"] = 831.5
        map["ARS"] = 837.615005
        map["AUD"] = 1.525734
        map["AWG"] = 1.8
        map["AZN"] = 1.7
        return map
    }

    fun getCurrencyRateListFromDB() :  List<CurrencyRate> {
        val res = ArrayList<CurrencyRate>()
        res.add(CurrencyRate(2,"WEW", "12.2332"))
        return  res
    }

    fun getLatestExchangeRateResponse() : LatestExchangeRateResponse {
        return LatestExchangeRateResponse(
            2313342,
            emptyMap()
        )
    }
}