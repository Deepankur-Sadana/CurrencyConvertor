package com.example.paypay

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.paypay.api.CurrencyApi
import com.example.paypay.db.CurrencyDataBase
import com.example.paypay.db.CurrencyRate
import com.example.paypay.screens.CurrencyScreen
import com.example.paypay.viewmodels.CurrencyPageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
     val TAG = "CURRENCY"

    @Inject
    lateinit var currencyApi: CurrencyApi

    lateinit var currencyDataBase: CurrencyDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currencyDataBase = Room.databaseBuilder(
            context = applicationContext,
            klass = CurrencyDataBase::class.java,
            name = "currencyDB"
        ).build()
        val viewModel = ViewModelProvider(this)
            .get(CurrencyPageViewModel::class.java)


        GlobalScope.launch {
//            currencyDataBase.currencyRateDao().insertAll(CurrencyRate(13,"EFE", "1243"))
//            currencyDataBase.currencyRateDao().insertAll(CurrencyRate(234,"4DD", "32434"))

            val symbols = "AED,INR,USD,EUR"
            delay(5000)
            val response = currencyApi.getLatestExchangeRates(symbols).body()
            Log.d(TAG , "$response")
            Log.d(TAG , "${response?.rates}")
        }

        CoroutineScope(Dispatchers.IO).launch {
            delay(10000)
            DummyLocalDataManager.getConvertedCurrency(applicationContext)
        }
        setContent {
            App()
        }
    }
}


@Composable
fun App() {
    if (DummyLocalDataManager.isDataLoaded.value) {
        CurrencyScreen(data = DummyLocalDataManager.data) {
        }
    } else {
        Box (contentAlignment = Alignment.Center, 
            modifier = Modifier.fillMaxSize(1f)){
            Text(text = "Loading...", style = MaterialTheme.typography.bodyLarge)

        }
    }

}
