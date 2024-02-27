package com.example.paypay

import com.example.paypay.db.CurrencyRateDao
import com.example.paypay.models.ConvertedCurrencyRate


interface IDataManager {
    suspend fun getData() : List<ConvertedCurrencyRate>
}
class DataManager(private val currencyRateDao: CurrencyRateDao) : IDataManager {
    override suspend fun getData(): List<ConvertedCurrencyRate> {
//        TODO()
        currencyRateDao.getAllCurrency()
        return emptyList()
    }
}