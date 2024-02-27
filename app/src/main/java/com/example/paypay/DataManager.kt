package com.example.paypay

import com.example.paypay.data.DataRefreshListener
import com.example.paypay.db.CurrencyRate
import com.example.paypay.db.CurrencyRateDao
import com.example.paypay.models.ConvertedCurrencyRate
import com.example.paypay.repository.RemoteRepository


interface IDataManager {
    suspend fun getData(dataRefreshListener: DataRefreshListener): List<ConvertedCurrencyRate>
    suspend fun getRefreshedData(): List<ConvertedCurrencyRate>
}

class DataManager(
    private val currencyRateDao: CurrencyRateDao,
    private val remoteRepository: RemoteRepository
) : IDataManager {
    override suspend fun getData(dataRefreshListener: DataRefreshListener): List<ConvertedCurrencyRate> {
        val data = currencyRateDao.getAllCurrency()
        return if (data.isEmpty().not()) {
            mapLocalData(data)
        } else {
            val dataFromServer = remoteRepository.getExchangeRatesList()
            dataRefreshListener.onDataRefreshed()
            saveServerDataToDB(dataFromServer)
            mapLocalData(dataFromServer)
        }
    }

    override suspend fun getRefreshedData(): List<ConvertedCurrencyRate> {
        val dataFromServer = remoteRepository.getExchangeRatesList()
        saveServerDataToDB(dataFromServer)
        return mapLocalData(dataFromServer)
    }

    private fun mapLocalData(dataFromServer: Map<String, Double>): List<ConvertedCurrencyRate> {
        val res = ArrayList<ConvertedCurrencyRate>()
        dataFromServer.forEach {
            res.add(ConvertedCurrencyRate(it.key, it.value))
        }
        return res
    }

    private suspend fun saveServerDataToDB(dataFromServer: Map<String, Double>) {
        val list = ArrayList<CurrencyRate>()
        var uid = 1
        dataFromServer.entries.forEach {
            list.add(CurrencyRate(uid = uid++, symbol = it.key, value = it.value.toString()))
        }
        currencyRateDao.insertAll(*list.toTypedArray())
    }

    private fun mapLocalData(
        dbData: List<CurrencyRate>
    ): List<ConvertedCurrencyRate> {
        val res = ArrayList<ConvertedCurrencyRate>()
        dbData.forEach {
            res.add(ConvertedCurrencyRate(it.symbol!!, it.value!!.toDouble()))
        }
        return res
    }
}