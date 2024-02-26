package com.example.paypay

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.paypay.api.CurrencyApi
import com.example.paypay.screens.CurrencyScreen
import com.example.paypay.ui.theme.PayPayTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch {
            val response = currencyApi.getCurrency()
            Log.d(TAG , "$response")
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

/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PayPayTheme {
        Greeting("Android")
    }
}*/
