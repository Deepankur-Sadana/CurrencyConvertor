package com.example.paypay.repository

import com.example.paypay.DataManager
import com.example.paypay.db.CurrencyRate
import com.example.paypay.db.CurrencyRateDao
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DataManagerTest {

    private val currencyRateDao = mock<CurrencyRateDao>()
    private val remoteRepository = mock<RemoteRepository>()
    @Test
    fun `when requested for data it queries local DB for exchange rates` () = runBlocking {
        //given
        val dataManager = DataManager(currencyRateDao, remoteRepository)

        whenever(currencyRateDao.getAllCurrency()).thenReturn(emptyList())

        //when
        dataManager.getData()

        //then
        verify(currencyRateDao).getAllCurrency()
        verifyNoMoreInteractions(currencyRateDao)
    }

    @Test
    fun `when local data is presented, it doesn't make a network call` () = runBlocking {
        //given
        val dataManager = DataManager(currencyRateDao, remoteRepository)

        val listOfStoredData = listOf(
            CurrencyRate(2, "USD", "1.0") ,
            CurrencyRate(3, "AED", "0.053") ,
            CurrencyRate(4, "EUR", "1.034") ,
        )
        whenever(currencyRateDao.getAllCurrency()).thenReturn(listOfStoredData)
        //when
        dataManager.getData()

        //then
        verify(currencyRateDao).getAllCurrency()
        verifyNoMoreInteractions(currencyRateDao)
    }
}