package com.example.paypay

import com.example.paypay.db.CurrencyRate
import com.example.paypay.db.CurrencyRateDao
import com.example.paypay.models.ConvertedCurrencyRate
import com.example.paypay.repository.RemoteRepository


interface IDataManager {
    suspend fun getData(): List<ConvertedCurrencyRate>
}

class DataManager(
    private val currencyRateDao: CurrencyRateDao,
    private val remoteRepository: RemoteRepository
) : IDataManager {
    override suspend fun getData(): List<ConvertedCurrencyRate> {
        val data = currencyRateDao.getAllCurrency()
        if (data.isEmpty()) {
            val dataFromServer = remoteRepository.getExchangeRatesList()
            saveServerDataToDB(dataFromServer)
        }
        return mapData(data)
    }

    private suspend fun saveServerDataToDB(dataFromServer: Map<String, Double>) {
        val list = ArrayList<CurrencyRate>()
        var uid = 1
        dataFromServer.entries.forEach {
            list.add(CurrencyRate(uid = uid++, symbol = it.key, value = it.value.toString()))
        }
        currencyRateDao.insertAll(*list.toTypedArray())
    }

    private fun mapData(
        dbData: List<CurrencyRate>
    ): List<ConvertedCurrencyRate> {
        val res = ArrayList<ConvertedCurrencyRate>()
        dbData.forEach{
            res.add(ConvertedCurrencyRate(it.symbol!!, it.value!!.toDouble()))
        }
        return res
    }
}