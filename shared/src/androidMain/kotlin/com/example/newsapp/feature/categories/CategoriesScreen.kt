package com.example.newsapp.feature.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.core.designsystem.theme.AppTheme
import com.example.newsapp.core.utils.rememberClick
import com.example.newsapp.core.widget.BorderCard
import com.example.newsapp.core.widget.TitleTopBar
import com.example.newsapp.feature.categories.presentation.categories.CategoriesAction
import com.example.newsapp.feature.categories.presentation.categories.CategoriesEvent
import com.example.newsapp.feature.categories.presentation.categories.CategoriesState
import com.example.newsapp.feature.categories.presentation.categories.CategoriesViewModel
import com.example.newsapp.feature.categories.presentation.model.CategoryUiModel
import org.koin.androidx.compose.getViewModel


@Composable
fun CategoriesScreen(
    navController: NavController,
    viewModel: CategoriesViewModel = getViewModel(),
) {
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsState(initial = null)
    val consumer = rememberClick<CategoriesEvent> { viewModel.obtainEvent(it) }

    CategoriesActions(navController, action)

    ScreenContent(state, consumer)
}

@Composable
private fun CategoriesActions(
    navController: NavController,
    action: CategoriesAction?,
) {
    LaunchedEffect(key1 = action) {
        when (action) {
            null -> Unit
            is CategoriesAction.NavigateToCategoryNews ->
                navController.navigate("news_category/${action.codeName}")
        }
    }
}

@Composable
private fun ScreenContent(
    state: CategoriesState,
    consumer: (CategoriesEvent) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxHeight(),
        containerColor = AppTheme.colors.background,
        topBar = {
            TitleTopBar(title = stringResource(id = R.string.categories))
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                start = AppTheme.padding._16dp,
                end = AppTheme.padding._16dp,
                top = AppTheme.padding._8dp,
                bottom = AppTheme.padding._8dp
            ),
            verticalArrangement = Arrangement.spacedBy(AppTheme.padding._16dp),
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            items(
                items = state.categories
            ) { news ->
                CategoryItem(news, consumer)
            }
        }
    }
}

@Composable
private fun CategoryItem(
    category: CategoryUiModel,
    consumer: (CategoriesEvent) -> Unit,
) {
    BorderCard(
        onClick = {
            consumer(CategoriesEvent.OnCategoryClick(category.codeName))
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.padding._8dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val icon = when (category.codeName) {
                "business" -> Icons.Filled.Call
                "entertainment" -> Icons.Filled.Star
                "general" -> Icons.Filled.AccountCircle
                "health" -> Icons.Filled.Add
                "science" -> Icons.Filled.Search
                "sports" -> Icons.Filled.ShoppingCart
                "technology" -> Icons.Filled.ShoppingCart
                else -> Icons.Filled.MailOutline
            }
            Icon(
                icon,
                null,
                tint = AppTheme.colors.primary,
                modifier = Modifier.padding(start = 8.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            )
            Text(
                text = category.name, style = AppTheme.typography.base
                    .copy(
                        fontSize = 20.sp
                    )
            )
        }
    }
}