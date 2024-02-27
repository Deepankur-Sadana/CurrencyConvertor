package com.example.paypay.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.paypay.viewmodels.CurrencyPageViewModel
import com.example.paypay.viewmodels.LatestCurrencyUiState

@Composable
fun App() {

    val viewModel: CurrencyPageViewModel = viewModel()
    val state = viewModel.uiState.collectAsState()

    when (state.value) {
        is LatestCurrencyUiState.Success -> {
            CurrencyScreen(
                data =
                (state.value as LatestCurrencyUiState.Success).convertedCurrencyRates
            ) {}
        }

        is LatestCurrencyUiState.Error -> {
            TODO("implement ERROR state")
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