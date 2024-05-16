package com.example.newsapp.feature.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.feature.home.presentation.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
) {
    val state by viewModel.viewStates.collectAsStateWithLifecycle()

    LazyColumn() {
        items(state.newsList) {
            Text(it.title.orEmpty())
        }
    }
}