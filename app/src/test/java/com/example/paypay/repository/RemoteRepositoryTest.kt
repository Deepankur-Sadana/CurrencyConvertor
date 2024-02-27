package com.example.paypay.repository

import com.example.paypay.api.CurrencyApi
import com.example.paypay.api.LatestExchangeRateResponse
import com.example.paypay.calculators.CurrencyFixtures.getLatestExchangeRateResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

class RemoteRepositoryTest {

    private val api = mock<CurrencyApi>()
    @Test
    fun `when asked to fetch exchange rates, it fetches the currency list first` () = runBlocking {
        //given
        val remoteRepository = RemoteRepository(api)

        whenever(api.getCurrencyList())
            .thenReturn(Response.success(emptyMap()))


        whenever(api.getLatestExchangeRates(any()))
            .thenReturn(Response.success(getLatestExchangeRateResponse()))

        //when
        remoteRepository.getExchangeRatesList()

        //then
        verify(api).getCurrencyList()
        verify(api).getLatestExchangeRates("")
        verifyNoMoreInteractions(api)
    }


    @Test
    fun `when asked to fetch exchange rates, it can make API call to get exchange rates` () = runBlocking {
        //given
        val remoteRepository = RemoteRepository(api)

        whenever(api.getCurrencyList())
            .thenReturn(Response.success(emptyMap()))

        whenever(api.getLatestExchangeRates(any()))
            .thenReturn(Response.success(getLatestExchangeRateResponse()))

        //when
        remoteRepository.getExchangeRatesList()

        //then
        verify(api).getCurrencyList()
        verify(api).getLatestExchangeRates(any())
        verifyNoMoreInteractions(api)
    }
}