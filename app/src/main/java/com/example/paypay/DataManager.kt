package com.example.paypay

import com.example.paypay.db.CurrencyRateDao
import com.example.paypay.models.ConvertedCurrencyRate
import com.example.paypay.repository.RemoteRepository


interface IDataManager {
    suspend fun getData() : List<ConvertedCurrencyRate>
}
class DataManager(
    private val currencyRateDao: CurrencyRateDao,
    private val remoteRepository: RemoteRepository
) : IDataManager {
    override suspend fun getData(): List<ConvertedCurrencyRate> {
        val data = currencyRateDao.getAllCurrency()
        if (data.isEmpty()) {
            remoteRepository.getCurrencyList()
        }
        return emptyList()
    }
}