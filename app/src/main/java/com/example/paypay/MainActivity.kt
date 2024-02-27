package com.example.paypay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.paypay.api.CurrencyApi
import com.example.paypay.db.CurrencyDataBase
import com.example.paypay.screens.CurrencyScreen
import com.example.paypay.viewmodels.CurrencyPageViewModel
import com.example.paypay.viewmodels.LatestCurrencyUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val TAG = "CURRENCY"

    @Inject
    lateinit var currencyApi: CurrencyApi

    lateinit var currencyDataBase: CurrencyDataBase
    lateinit var viewModel: CurrencyPageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currencyDataBase = Room.databaseBuilder(
            context = applicationContext,
            klass = CurrencyDataBase::class.java,
            name = "currencyDB"
        ).build()
        viewModel = ViewModelProvider(this)
            .get(CurrencyPageViewModel::class.java)



        CoroutineScope(Dispatchers.IO).launch {
//            delay(10000)
//            DummyLocalDataManager.getConvertedCurrency(applicationContext)
        }
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val viewModel: CurrencyPageViewModel = viewModel()
    val state = viewModel.uiState.collectAsState()

    when (state.value) {
        is LatestCurrencyUiState.Success -> {
            CurrencyScreen(data =
            (state.value as LatestCurrencyUiState.Success).convertedCurrencyRates) {

            }

        }

        is LatestCurrencyUiState.Error -> {
            TODO("implement loading state")
        }

        LatestCurrencyUiState.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize(1f)
            ) {
                Text(text = "Loading...", style = MaterialTheme.typography.bodyLarge)

            }
        }
    }
}
