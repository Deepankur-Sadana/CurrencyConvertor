package com.example.paypay.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CurrencyRateDao {

    @Query("SELECT * FROM currencyrate")
    suspend fun getAllCurrency(): List<CurrencyRate>

    @Insert
    suspend fun insertAll(vararg currency: CurrencyRate)

    @Query("DELETE FROM currencyrate")
    fun deleteAllEntries()
}

