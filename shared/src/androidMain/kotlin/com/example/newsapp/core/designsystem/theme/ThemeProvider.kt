package com.example.newsapp.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.newsapp.core.designsystem.theme.values.Colors
import com.example.newsapp.core.designsystem.theme.values.CornerShape
import com.example.newsapp.core.designsystem.theme.values.Padding
import com.example.newsapp.core.designsystem.theme.values.Typography

// Use this class to provide colors, fonts, paddings, and etc...
object AppTheme {

    val colors: Colors
        @Composable
        get() = LocalCustomColors.current

    val typography: Typography
        @Composable
        get() = LocalCustomTypography.current

    val padding: Padding
        @Composable
        get() = LocalCustomPadding.current

    val cornerShape: CornerShape
        @Composable
        get() = LocalCustomCornerShape.current
}

val LocalCustomColors = staticCompositionLocalOf<Colors> {
    error("No colors provided")
}

val LocalCustomTypography =
    staticCompositionLocalOf<Typography> {
        error("No font provided")
    }

val LocalCustomPadding = staticCompositionLocalOf<Padding> {
    error("No padding provided")
}

val LocalCustomCornerShape = staticCompositionLocalOf<CornerShape> {
    error("No corner shape provided")
}