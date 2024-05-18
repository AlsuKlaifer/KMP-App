package com.example.newsapp.feature.categories

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.feature.categories.presentation.CategoriesState
import com.example.newsapp.feature.categories.presentation.CategoriesViewModel


@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = viewModel(),
) {
    val state by viewModel.viewStates.collectAsStateWithLifecycle()

    Text(text = "CategoriesScreen ")

    ScreenContent(state)
}

@Composable
private fun ScreenContent(
    state: CategoriesState,
) {

}