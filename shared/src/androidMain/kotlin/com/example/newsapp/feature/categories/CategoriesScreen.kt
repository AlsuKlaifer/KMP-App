package com.example.newsapp.feature.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.core.designsystem.theme.AppTheme
import com.example.newsapp.core.widget.BorderCard
import com.example.newsapp.core.widget.TitleTopBar
import com.example.newsapp.feature.categories.presentation.CategoriesState
import com.example.newsapp.feature.categories.presentation.CategoriesViewModel
import com.example.newsapp.feature.categories.presentation.model.CategoryUiModel
import org.koin.androidx.compose.getViewModel


@Composable
fun CategoriesScreen(
    navController: NavController,
    viewModel: CategoriesViewModel = getViewModel(),
) {
    val state by viewModel.viewStates.collectAsStateWithLifecycle()

    ScreenContent(state, navController)
}

@Composable
private fun ScreenContent(
    state: CategoriesState,
    navController: NavController,
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
                CategoryItem(news, navController)
            }
        }
    }
}

@Composable
private fun CategoryItem(
    category: CategoryUiModel,
    navController: NavController,
) {
    BorderCard(
        onClick = {
            //navController.navigate("detail/${article.title}")
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.padding._8dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Filled.MailOutline,
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