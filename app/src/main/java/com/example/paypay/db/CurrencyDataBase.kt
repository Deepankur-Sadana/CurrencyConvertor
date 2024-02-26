package com.example.paypay.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyRate::class], version = 1)
abstract class CurrencyDataBase : RoomDatabase() {
    abstract fun userDao(): CurrencyRateDao
}

