package com.example.paypay

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.paypay.api.CurrencyApi
import com.example.paypay.db.CurrencyDataBase
import com.example.paypay.db.CurrencyRate
import com.example.paypay.screens.CurrencyScreen
import com.example.paypay.viewmodels.CurrencyPageViewModel
import com.example.paypay.viewmodels.LatestCurrencyUiState
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
/*@Preview
@Composable
fun dropDown() {

}*/
