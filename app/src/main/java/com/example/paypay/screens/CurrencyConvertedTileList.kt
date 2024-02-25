package com.example.paypay.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.paypay.models.ConvertedRate

@Composable
fun convertedCurrencyList(data : Array<ConvertedRate>, onClick : ()-> Unit){
    LazyColumn(content = {
        items(data) {
            CurrencyTileItem(convertedRate = it) {
                onClick()
            }
        }
    })

}


@Composable
fun test() {
    val dates = MutableList(31) { it }

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .systemBarsPadding(),
        columns = GridCells.Fixed(7),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(dates) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = "${it + 1}")
            }
        }
    }
}