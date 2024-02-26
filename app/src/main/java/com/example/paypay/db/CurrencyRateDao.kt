package com.example.paypay.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CurrencyRateDao {

    @Query("SELECT * FROM currencyrate")
    fun getAllCurrency(): LiveData<List<CurrencyRate>>

    @Insert
    suspend fun insertAll(vararg currency: CurrencyRate)
}

