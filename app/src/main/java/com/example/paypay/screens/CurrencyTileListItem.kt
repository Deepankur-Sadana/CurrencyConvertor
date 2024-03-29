package com.example.paypay.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.paypay.models.ConvertedCurrencyRate

@Preview
@Composable
fun CurrencyTileItem(
    convertedCurrencyRate : ConvertedCurrencyRate,
    onclick : () -> Unit
) {
    Card( elevation = CardDefaults.cardElevation(),
        modifier = Modifier.padding(4.dp)
    ) {
        Row(modifier = Modifier.padding(6.dp)) {

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = convertedCurrencyRate.currencySymbol,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    minLines = 2,
                    maxLines = 2,
                    text = convertedCurrencyRate.convertedValue.toString(),
                    style = MaterialTheme.typography.titleLarge
                )

            }
        }

    }

}

@Preview
@Composable
fun details() {
    Box(
        modifier = Modifier.fillMaxSize(1f)
            .background(
                Brush.radialGradient(
                    colors = listOf(
                    Color(0xFF000000),
                    Color(0xFF00FF00)
                )
            )
    ))
}