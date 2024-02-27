package com.example.paypay.calculators

import com.example.paypay.calculators.CurrencyFixtures.getCurrencyMap
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Test

class CurrencyInterConverterTest {

    private val currencyConvertedErrorListener = mock<CurrencyConvertedErrorListener>()
    @Test
    fun `it can accept a valid input` () {
        val currencyInterConverter = CurrencyInterConverter(currencyConvertedErrorListener)
        currencyInterConverter.loadCurrencyMap(getCurrencyMap())
        verifyNoMoreInteractions(currencyConvertedErrorListener)
    }

    val USD = "USD"
    @Test
    fun `when USD value is not present the conversion rates map, it notifies via an error` () {
        val mapWithoutUSD = getMapWithoutUSD()
        val currencyInterConverter = CurrencyInterConverter(currencyConvertedErrorListener)
        currencyInterConverter.loadCurrencyMap(mapWithoutUSD)

        verify(currencyConvertedErrorListener).onError()
        verifyNoMoreInteractions(currencyConvertedErrorListener)


    }
    @Test
    fun `if the conversion rates are not bound to USD (base rate of USD = 1), it throws error` () {
        val currencyInterConverter = CurrencyInterConverter(currencyConvertedErrorListener)
        val map = getCurrencyMap()
        map[USD] = 12.22
        currencyInterConverter.loadCurrencyMap(map)
        verify(currencyConvertedErrorListener).onError()
        verifyNoMoreInteractions(currencyConvertedErrorListener)
    }

    private fun getMapWithoutUSD(): HashMap<String, Double> {
        val map = HashMap(getCurrencyMap())
        map.remove(USD)
        return map
    }




}