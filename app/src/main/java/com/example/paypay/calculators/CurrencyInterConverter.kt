package com.example.paypay.calculators

import com.example.paypay.models.ConvertedCurrencyRate

/**
 * Currency converter will just change the base rate of supplied currency to 1
 *
 * if an amount is supplied, then the entire list is multiplied with the amount
 *
 */
interface ICurrencyInterConverter {
    fun convertTo(toCurrencyCode: String, amount: Double): List<ConvertedCurrencyRate>
    fun loadCurrencyMap(map: HashMap<String, Double>)
    fun loadCurrencyList(list: List<ConvertedCurrencyRate>)
    fun updateAmount(newAmount: Double): List<ConvertedCurrencyRate>
}

class CurrencyInterConverter(
    private val currencyConvertedErrorListener: CurrencyConvertedErrorListener? = null
) : ICurrencyInterConverter {

    private  var currencyRates = ArrayList<ConvertedCurrencyRate>()


    override fun loadCurrencyMap(map: HashMap<String, Double>) {
        if (map["USD"] == null || map["USD"] != 1.00) {
            currencyConvertedErrorListener?.onError()
        }
    }

    override fun loadCurrencyList(list: List<ConvertedCurrencyRate>) {
        this.currencyRates = ArrayList(list)
    }

    override fun updateAmount(newAmount: Double) :List<ConvertedCurrencyRate> {
        val updatedAmountList = ArrayList<ConvertedCurrencyRate>()
        currencyRates.forEach {
            updatedAmountList.add(ConvertedCurrencyRate(it.currencySymbol, it.convertedValue * newAmount))
        }
        return updatedAmountList
    }


    override fun convertTo(toCurrencyCode: String, amount: Double): List<ConvertedCurrencyRate> {
        var existingRateOfCurrency = -1.0
        for (i in currencyRates.indices) {
            val currencySymbol = currencyRates[i].currencySymbol
            if (currencySymbol == toCurrencyCode) {
                existingRateOfCurrency = currencyRates[i].convertedValue
                break
            }
        }

        val updatedAmountList = ArrayList<ConvertedCurrencyRate>()
        currencyRates.forEach {
            updatedAmountList.add(ConvertedCurrencyRate(it.currencySymbol, it.convertedValue * amount))
        }
        return updatedAmountList
    }

}