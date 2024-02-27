package com.example.paypay.calculators


interface ICurrencyInterConverter {

}
class CurrencyInterConverter(
    private val currencyConvertedErrorListener: CurrencyConvertedErrorListener? = null
) : ICurrencyInterConverter {


    fun loadCurrencyMap(map: HashMap<String, Double>) {
        if (map["USD"] == null || map["USD"] != 1.00) {
            currencyConvertedErrorListener?.onError()
        }
    }


    fun convertTo(toCurrencyCode: String) {

    }

}