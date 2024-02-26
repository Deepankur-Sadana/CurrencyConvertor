package com.example.paypay.calculators


class CurrencyInterConverter(
    private val currencyConvertedErrorListener: CurrencyConvertedErrorListener
) {


    fun loadCurrencyMap(map: HashMap<String, Double>) {
        if (map["USD"] == null || map["USD"] != 1.00) {
            currencyConvertedErrorListener.onError()
        }
    }


    fun convertTo(toCurrencyCode: String) {

    }

}