package com.example.paypay

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.paypay.models.ConvertedCurrencyRate
import com.google.gson.Gson

object DummyLocalDataManager {

    var isDataLoaded = mutableStateOf(false)
    val data = Array<ConvertedCurrencyRate>(21) { ConvertedCurrencyRate("asd", 23.00) }
    fun getCurrencyLost(context: Context) {
        val inputStream = context.assets.open("currency_list.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
//        data = gson.fromJson(json , Array<Map>::class.java)
    }

    fun getConvertedCurrency(context: Context) {
        val inputStream = context.assets.open("conversion_response.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        isDataLoaded.value = true

    }


}