package com.example.newsapp.feature.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.core.designsystem.theme.AppTheme
import com.example.newsapp.core.widget.BackTopBar
import com.example.newsapp.core.widget.BaseImage
import com.example.newsapp.core.widget.asBgColor
import com.example.newsapp.feature.home.presentation.HomeState
import com.example.newsapp.feature.home.presentation.HomeViewModel
import com.example.newsapp.feature.news.data.response.Article

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
) {
    val state by viewModel.viewStates.collectAsStateWithLifecycle()

    ScreenContent(state)

}

@Composable
private fun ScreenContent(
    state: HomeState,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxHeight(),
        containerColor = AppTheme.colors.background,
        topBar = {
            BackTopBar(title = "News")
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            items(
                items = state.newsList
            ) { news ->
                NewsItem(news)
            }
        }
    }
}

@Composable
private fun NewsItem(
    article: Article,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .asBgColor(),
        shape = AppTheme.cornerShape.rounded12dp,
        border = BorderStroke(2.dp, AppTheme.colors.grayText),
        elevation = CardDefaults.cardElevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp, 0.dp),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            if (!article.urlToImage.isNullOrBlank()) {
                BaseImage(
                    model = article.urlToImage,
                    requestBuilderTransform = { it.fitCenter() },
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.padding(4.dp))
            }
            Text(
                text = article.title ?: "",
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