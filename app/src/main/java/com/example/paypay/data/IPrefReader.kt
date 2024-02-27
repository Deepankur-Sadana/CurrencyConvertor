package com.example.paypay.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

interface IPrefReader {
    fun getLastStoredEpochTime() : Long?
    fun updateRefreshTime(time : Long)
}

class PreferenceReader(val context : Context) : IPrefReader {
    private val fileName = "defaultPres"
    private val lastStoredTime = "last_stored_epoch_time"
    private val  sharedPreferences: SharedPreferences =
        context.getSharedPreferences(fileName, MODE_PRIVATE);
    override fun getLastStoredEpochTime(): Long? {
        val res = sharedPreferences.getLong(lastStoredTime , 0)
        if (res.toInt() == 0) return null
        return res
    }

    override fun updateRefreshTime(time: Long) {
        sharedPreferences.edit().putLong(lastStoredTime, time).commit()
    }

}
