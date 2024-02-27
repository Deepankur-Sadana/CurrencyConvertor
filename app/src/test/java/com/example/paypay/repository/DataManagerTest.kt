package com.example.paypay.repository

import com.example.paypay.DataManager
import com.example.paypay.db.CurrencyRateDao
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DataManagerTest {

    private val currencyRateDao = mock<CurrencyRateDao>()
    @Test
    fun `when requested for data it queries local DB for exchange rates` () = runBlocking {
        //given
        val dataManager = DataManager(currencyRateDao)

        //when
        dataManager.getData()

        //then
        verify(currencyRateDao).getAllCurrency()
        verifyNoMoreInteractions(currencyRateDao)
    }
}