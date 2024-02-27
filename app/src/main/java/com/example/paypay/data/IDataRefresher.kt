package com.example.paypay.data

import com.example.paypay.IDataManager

interface IDataRefresher {
    suspend fun checkAndRefreshData()
}

class DataRefresher(
    private val prefReader: IPrefReader,
    private val dataManager: IDataManager,
    private val timeFetcher: IOSTimeFetcher
) : IDataRefresher {

    @Volatile
    private var refreshInProgrees = false
    override suspend fun checkAndRefreshData() {
        if (refreshInProgrees) return
        val systemTime = timeFetcher.getEpochTime()
        prefReader.getLastStoredEpochTime()?.let { lastStoredEpoch ->
            // Convert the epoch time to minutes

            val minutes = (systemTime - lastStoredEpoch) / 60000
            if (minutes >= 30) {
                refreshInProgrees = true
                dataManager.getRefreshedData()
                refreshInProgrees = false
            }
        }
    }

}