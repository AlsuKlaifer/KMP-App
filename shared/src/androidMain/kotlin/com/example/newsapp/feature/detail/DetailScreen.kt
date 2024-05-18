package com.example.newsapp.feature.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailScreen(
    detailId : Int
) {
    Text(text = "Detail $detailId")
}