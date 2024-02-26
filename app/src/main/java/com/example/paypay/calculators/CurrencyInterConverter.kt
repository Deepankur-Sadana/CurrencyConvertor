package com.example.paypay.calculators


class CurrencyInterConverter(
    private val currencyConvertedErrorListener: CurrencyConvertedErrorListener
) {


    fun loadCurrencyMap(map: HashMap<String, Float>) {
        if (map["USD"] == null || map["USD"] != 1F) {
            currencyConvertedErrorListener.onError()
        }
    }
    fun convertTo(toCurrencyCode: String) {

    }

}