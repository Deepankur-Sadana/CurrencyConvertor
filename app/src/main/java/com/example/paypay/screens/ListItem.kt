package com.example.paypay.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun CurrencyTileItem() {
    Card( elevation = CardDefaults.cardElevation(),
        modifier = Modifier.padding(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                imageVector = Icons.Filled.Build,
                colorFilter = ColorFilter.tint(Color.White),
                alignment = Alignment.Center,
                contentDescription = "Image",
                modifier = Modifier.background(Color.Black)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "USD/INR",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "1345.456",
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
//        contentAlignment = Alignment.Center,
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