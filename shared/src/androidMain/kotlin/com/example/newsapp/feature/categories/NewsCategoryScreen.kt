package com.example.newsapp.feature.categories

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.newsapp.core.designsystem.theme.AppTheme
import com.example.newsapp.core.utils.rememberClick
import com.example.newsapp.core.widget.BackTopBar
import com.example.newsapp.core.widget.BaseImage
import com.example.newsapp.core.widget.BorderCard
import com.example.newsapp.feature.categories.presentation.newscategory.NewsCategoryAction
import com.example.newsapp.feature.categories.presentation.newscategory.NewsCategoryEvent
import com.example.newsapp.feature.categories.presentation.newscategory.NewsCategoryState
import com.example.newsapp.feature.categories.presentation.newscategory.NewsCategoryViewModel
import com.example.newsapp.feature.news.data.model.response.Article
import com.valentinilk.shimmer.shimmer
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import java.util.Locale

@Composable
fun NewsCategoryScreen(
    categoryCodeName: String,
    navController: NavController,
    viewModel: NewsCategoryViewModel = getViewModel
        (parameters = { parametersOf(categoryCodeName) }
    ),
) {
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsState(initial = null)
    val consumer = rememberClick<NewsCategoryEvent> { viewModel.obtainEvent(it) }

    NewsCategoryActions(navController, action)

    ScreenContent(state, consumer)

}

@Composable
private fun NewsCategoryActions(
    navController: NavController,
    action: NewsCategoryAction?,
) {
    LaunchedEffect(key1 = action) {
        when (action) {
            is NewsCategoryAction.NavigateToDetails -> {
                navController.navigate("detail/${action.title}")
            }

            null -> Unit
            NewsCategoryAction.NavigateBack -> navController.navigateUp()
        }
    }
}

@Composable
private fun ScreenContent(
    state: NewsCategoryState,
    consumer: (NewsCategoryEvent) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxHeight(),
        containerColor = AppTheme.colors.background,
        topBar = {
            BackTopBar(title = state.category.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ENGLISH
                ) else it.toString()
            }) {
                consumer(NewsCategoryEvent.OnBackClicked)
            }
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
                NewsItem(news, consumer)
            }
        }
    }
}


@Composable
private fun NewsItem(
    article: Article,
    consumer: (NewsCategoryEvent) -> Unit,
) {
    BorderCard(
        onClick = { consumer(NewsCategoryEvent.OnArticleClicked(article)) }
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
                style = AppTheme.typography.baseBold.copy(
                    fontSize = 18.sp,
                )
            )
            if (!article.author.isNullOrBlank()) {
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = article.author,
                    style = AppTheme.typography.base.copy(
                        fontSize = 14.sp,
                        color = AppTheme.colors.grayText
                    )

                )
            }
        }
    }
}