package com.example.newsapp.core.designsystem.theme.values

import androidx.compose.ui.text.TextStyle
import com.example.newsapp.core.designsystem.theme.values.Typography
import com.example.newsapp.core.designsystem.theme.values.baseDarkPalette
import com.example.newsapp.core.designsystem.theme.values.baseLightPalette

internal val baseLightTypography = Typography(
    base = TextStyle(
        color = baseLightPalette.grayText
    )

)

internal val baseDarkTypography = Typography(
    base = TextStyle(
        color = baseDarkPalette.grayText
    )
)