package com.example.paypay.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CurrencyRate::class], version = 1)
abstract class CurrencyDataBase : RoomDatabase() {

    companion object {
        private var currencyDataBase: CurrencyDataBase? = null
        fun getInstance(context: Context) : CurrencyDataBase {
            if (currencyDataBase == null) {
                synchronized(this) {
                    if (currencyDataBase == null) {
                        currencyDataBase = Room.databaseBuilder(
                            context = context,
                            klass = CurrencyDataBase::class.java,
                            name = "currencyDB"
                        ).build()
                    }
                }
            }
            return  currencyDataBase!!
        }
    }

    abstract fun currencyRateDao(): CurrencyRateDao
}

