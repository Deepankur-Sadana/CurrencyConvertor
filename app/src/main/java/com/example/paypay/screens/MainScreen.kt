package com.example.paypay.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.paypay.models.ConvertedCurrencyRate
import com.example.paypay.viewmodels.CurrencyPageViewModel

@Composable
fun CurrencyScreen(
    data: List<ConvertedCurrencyRate>,
    onClick: () -> Unit
) {

    val viewModel: CurrencyPageViewModel = viewModel()
    val state = viewModel.uiState.collectAsState()
    Column {
        Spacer(modifier = Modifier.padding(16.dp))

        val state = remember { mutableStateOf("1") }
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            value = state.value,
            onValueChange = {
                state.value = it
                viewModel.updateInputAmount(it.toDouble())
            },
            label = { Text("Enter amount here") }
        )

        var selectedIndex by remember { mutableStateOf(-1) }
        LargeDropdownMenu(
            label = "Currencies",
            items = data.map { it.currencySymbol },
            selectedIndex = selectedIndex,
            onItemSelected = { index, _ -> selectedIndex = index },
        )

        ConvertedCurrencyListGrid(data = data) {
            onClick
        }
    }

}
