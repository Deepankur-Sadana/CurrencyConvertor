package com.example.paypay.data

import com.example.paypay.IDataManager
import org.junit.Test
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking

class DataRefresherTest {
    private val prefReader = mock<IPrefReader>()
    private val timeFetcher = mock<IOSTimeFetcher>()
    private val dataManager = mock<IDataManager>()

    @Test
    fun `it can refresh the data if the data is stale, older than 30 mins`() = runBlocking {
        //given
        val dataRefresher = DataRefresher(prefReader, dataManager, timeFetcher)

        whenever(prefReader.getLastStoredEpochTime())
            .thenReturn(0)

        whenever(timeFetcher.getEpochTime())
            .thenReturn(Long.MAX_VALUE)

        //when
        dataRefresher.checkAndRefreshData()

        //then
        verify(dataManager).getRefreshedData()
        verifyNoMoreInteractions(dataManager)
    }
    @Test
    fun `it doesn't refresh the data if the epoch expiry is less than 30 mins`() = runBlocking {
        //given
        val dataRefresher = DataRefresher(prefReader, dataManager, timeFetcher)

        whenever(prefReader.getLastStoredEpochTime())
            .thenReturn(10)
        whenever(timeFetcher.getEpochTime())
            .thenReturn(12)

        //when
        dataRefresher.checkAndRefreshData()

        //then
        verifyNoMoreInteractions(dataManager)
    }

    @Test
    fun `if TTL is not present , it doesn't refresh the data`() = runBlocking {
        //given
        val dataRefresher = DataRefresher(prefReader, dataManager, timeFetcher)

        whenever(prefReader.getLastStoredEpochTime())
            .thenReturn(null)

        //when
        dataRefresher.checkAndRefreshData()

        //then
        verifyNoMoreInteractions(dataManager)
    }

    @Test
    fun `it can save data refreshed TTL`() = runBlocking {
        //given
        val dataRefresher = DataRefresher(prefReader, dataManager, timeFetcher)

        whenever(prefReader.getLastStoredEpochTime())
            .thenReturn(null)

        //when
        dataRefresher.updateRefreshTime(12)

        //then
        verify(prefReader).updateRefreshTime(12)
        verifyNoMoreInteractions(dataManager)
    }





}