package com.example.paypay

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.nio.charset.Charset

class DummyLocalDataManager {

    val data = ArrayList<Map<String, String>>()
    fun loadAssetsFromFile(context : Context) {
        val inputStream = context.assets.open("currency_list.json")
        val size : Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
//        data = gson.fromJson(json , Array<Map>::class.java)
    }
}