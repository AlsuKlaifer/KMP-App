package com.example.newsapp.core.designsystem.theme.values

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

internal val baseLightTypography = Typography(
    base = TextStyle(
        color = baseLightPalette.grayText
    ),
    baseBold = TextStyle(
        color = baseLightPalette.grayText,
        fontWeight = FontWeight.Bold
    )

)

internal val baseDarkTypography = Typography(
    base = TextStyle(
        color = baseDarkPalette.onBackground,
    ),
    baseBold = TextStyle(
        color = baseDarkPalette.onBackground,
        fontWeight = FontWeight.Bold
    )
)