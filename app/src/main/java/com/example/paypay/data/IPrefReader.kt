package com.example.paypay.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.provider.Settings.Global.getString

interface IPrefReader {
    fun getLastStoredEpochTime() : Long?
}

class PreferenceReader(val context : Context) : IPrefReader {
    val fileName = "defaultPres"
    val lastStoredTime = "last_stored_epoch_time"
    val  shared = context.getSharedPreferences(fileName, MODE_PRIVATE);
    override fun getLastStoredEpochTime(): Long? {
        val res = shared.getLong(lastStoredTime , 0)
        if (res.toInt() == 0) return null
        return res
    }

}
