package com.example.paypay.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyRate(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "symbol") val symbol: String?,
    @ColumnInfo(name = "value") val value: String?
)