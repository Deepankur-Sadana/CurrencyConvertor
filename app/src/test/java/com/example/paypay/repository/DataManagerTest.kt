package com.example.paypay.repository

import com.example.paypay.DataManager
import com.example.paypay.calculators.CurrencyFixtures
import com.example.paypay.calculators.CurrencyFixtures.getCurrencyRateListFromDB
import com.example.paypay.db.CurrencyRate
import com.example.paypay.db.CurrencyRateDao
import com.example.paypay.models.ConvertedCurrencyRate
import com.google.common.truth.Truth
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

        whenever(currencyRateDao.getAllCurrency())
            .thenReturn(getCurrencyRateListFromDB())

//        whenever(remoteRepository.getExchangeRatesList())
//            .thenReturn(CurrencyFixtures.getCurrencyMap())

        //when
        dataManager.getData(dataRefresher.getDataRefreshListener())

        //then
        verify(currencyRateDao).getAllCurrency()
        verifyNoMoreInteractions(currencyRateDao)
        verifyNoMoreInteractions(remoteRepository)
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
        dataManager.getData(dataRefresher.getDataRefreshListener())

        //then
        verify(currencyRateDao).getAllCurrency()
        verifyNoMoreInteractions(currencyRateDao)
    }

    @Test
    fun `when the local data is present it can perform apt conversion to DAO objects and give processed objects` () = runBlocking {
        //given
        val dataManager = DataManager(currencyRateDao, remoteRepository)

        val listOfStoredData = listOf(
            CurrencyRate(2, "USD", "1.0") ,
            CurrencyRate(3, "AED", "0.053") ,
            CurrencyRate(4, "EUR", "1.034") ,
        )
        whenever(currencyRateDao.getAllCurrency()).thenReturn(listOfStoredData)
        //when
        val result = dataManager.getData(dataRefresher.getDataRefreshListener())

        val excepted =  listOf(
            ConvertedCurrencyRate( "USD", 1.0) ,
            ConvertedCurrencyRate( "AED", 0.053) ,
            ConvertedCurrencyRate( "EUR", 1.034) ,
        )

        //then
        verify(currencyRateDao).getAllCurrency()
        verifyNoMoreInteractions(currencyRateDao)

        Truth.assertThat(result.size).isEqualTo(3)
//        Truth.assertThat(result).isEqualTo(excepted);
    }

    @Test
    fun `when data is fetched From Server, it saves it into Local DB` () = runBlocking {
        //given
        val dataManager = DataManager(currencyRateDao, remoteRepository)

        whenever(currencyRateDao.getAllCurrency()).thenReturn(emptyList())
        whenever(remoteRepository.getExchangeRatesList())
            .thenReturn(CurrencyFixtures.getCurrencyMap())

        //when
        val result = dataManager.getData(dataRefresher.getDataRefreshListener())

        //then
        verify(currencyRateDao).getAllCurrency()

        Truth.assertThat(result.size).isEqualTo(6)
    }
}