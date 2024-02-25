package com.example.paypay.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import com.example.paypay.models.ConvertedCurrencyRate

@Composable
fun CurrencyScreen(data : Array<ConvertedCurrencyRate>, onClick : () -> Unit) {
    Column {
        Text(text = "Sample App")
    }

    TextField(
        value = "text",
        onValueChange = { /*text = it */},
        label = { Text("Enter amount here") }
    )
    ConvertedCurrencyListGrid(data = data) {
        onClick
    }

}