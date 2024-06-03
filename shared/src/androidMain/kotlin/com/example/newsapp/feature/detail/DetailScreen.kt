package com.example.newsapp.feature.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.core.designsystem.theme.AppTheme
import com.example.newsapp.core.utils.rememberClick
import com.example.newsapp.core.widget.BackTopBar
import com.example.newsapp.core.widget.BaseImage
import com.example.newsapp.feature.detail.presentation.DetailAction
import com.example.newsapp.feature.detail.presentation.DetailEvent
import com.example.newsapp.feature.detail.presentation.DetailState
import com.example.newsapp.feature.detail.presentation.DetailViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DetailScreen(
    title: String,
    navController: NavController,
    viewModel: DetailViewModel = getViewModel(parameters = { parametersOf(title) }),
) {

    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsState(initial = null)
    val consumer = rememberClick<DetailEvent> { viewModel.obtainEvent(it) }

    DetailsActions(navController, action = action)

    ScreenContent(state, consumer)
}

@Composable
private fun DetailsActions(
    navController: NavController,
    action: DetailAction?,
) {
    LaunchedEffect(key1 = action) {
        when (action) {
            DetailAction.NavigateBack -> navController.navigateUp()
            null -> Unit
        }
    }
}

@Composable
private fun ScreenContent(
    state: DetailState,
    consumer: (DetailEvent) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxHeight(),
        containerColor = AppTheme.colors.background,
        topBar = {
            BackTopBar(title = stringResource(id = R.string.news)) {

                consumer(DetailEvent.OnBackClicked)
            }
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(AppTheme.padding._8dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            item {
                state.article?.urlToImage?.let {
                    BaseImage(
                        model = state.article.urlToImage,
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                    )
                }
            }

            item {
                Text(
                    text = state.article?.title.orEmpty(),
                    style = AppTheme.typography.baseBold.copy(
                        fontSize = 22.sp
                    )
                )
            }

            item {
                Text(
                    text = state.article?.author.orEmpty(),
                    style = AppTheme.typography.base.copy(
                        fontSize = 18.sp,
                        color = AppTheme.colors.grayText
                    )
                )
            }

            item {
                Text(
                    text = state.article?.content.orEmpty(),
                    style = AppTheme.typography.base.copy(
                        fontSize = 20.sp,
                    )
                )
            }
        }
    }
}