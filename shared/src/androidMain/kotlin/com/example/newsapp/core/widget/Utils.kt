package com.example.newsapp.core.widget

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.example.newsapp.core.designsystem.theme.AppTheme

fun Modifier.asBgColor(): Modifier = composed { this.background(AppTheme.colors.background) }