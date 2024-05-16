package com.example.newsapp.core.widget

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideSubcomposition
import com.bumptech.glide.integration.compose.RequestBuilderTransform
import com.bumptech.glide.integration.compose.RequestState
import com.example.newsapp.core.designsystem.theme.AppTheme
import com.valentinilk.shimmer.shimmer

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BaseImage(
    model: Any,
    modifier: Modifier = Modifier,
    requestBuilderTransform: RequestBuilderTransform<Drawable> = { it },
    loadingPlaceholder: @Composable () -> Unit = {
        Box(
            modifier = Modifier
                .shimmer()
                .fillMaxSize()
                .background(
                    color = AppTheme.colors.onBackground,
                    shape = AppTheme.cornerShape.rounded12dp
                )
        )
    },
    errorPlaceholder: @Composable () -> Unit = {},
) {
    GlideSubcomposition(
        model = model,
        requestBuilderTransform = requestBuilderTransform,
        modifier = modifier.clip(AppTheme.cornerShape.rounded12dp)
    ) {
        when (state) {
            RequestState.Failure -> errorPlaceholder()
            RequestState.Loading -> loadingPlaceholder()
            is RequestState.Success -> Image(painter, contentDescription = null)
        }
    }
}
