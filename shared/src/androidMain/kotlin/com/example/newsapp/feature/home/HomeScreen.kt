package com.example.newsapp.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.core.designsystem.theme.AppTheme
import com.example.newsapp.core.widget.BaseImage
import com.example.newsapp.core.widget.BorderCard
import com.example.newsapp.core.widget.TitleTopBar
import com.example.newsapp.feature.home.presentation.HomeState
import com.example.newsapp.feature.home.presentation.HomeViewModel
import com.example.newsapp.feature.news.data.model.response.Article
import com.valentinilk.shimmer.shimmer

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel(),
) {
    val state by viewModel.viewStates.collectAsStateWithLifecycle()

    ScreenContent(state, navController)

}

@Composable
private fun ScreenContent(
    state: HomeState,
    navController: NavController,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxHeight(),
        containerColor = AppTheme.colors.background,
        topBar = {
            TitleTopBar(title = stringResource(id = R.string.news))
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
                items = state.newsList
            ) { news ->
                NewsItem(news, navController)
            }
        }
    }
}

@Composable
private fun NewsItem(
    article: Article,
    navController: NavController,
) {
    BorderCard(
        onClick = { navController.navigate("detail/${article.title}") }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.padding._16dp),
        ) {
            if (!article.urlToImage.isNullOrBlank()) {
                BaseImage(
                    model = article.urlToImage,
                    requestBuilderTransform = { it.fitCenter() },
                    modifier = Modifier.fillMaxWidth(),
                    loadingPlaceholder = {
                        Box(
                            modifier = Modifier
                                .shimmer()
                                .fillMaxWidth()
                                .height(200.dp)
                                .background(
                                    color = AppTheme.colors.onBackground,
                                    shape = AppTheme.cornerShape.rounded12dp
                                )
                        )
                    }
                )
                Spacer(modifier = Modifier.padding(4.dp))
            }
            Text(
                text = article.title.orEmpty(),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            if (!article.author.isNullOrBlank()) {
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = article.author, style = TextStyle(
                        fontSize = 13.sp,
                        color = AppTheme.colors.grayText
                    )
                )
            }
        }
    }
}