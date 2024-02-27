package com.example.paypay.data

interface IOSTimeFetcher {
    fun getEpochTime() : Long
}

class OSTimeFetcher : IOSTimeFetcher {
    override fun getEpochTime(): Long {
        return System.currentTimeMillis()
    }

}
