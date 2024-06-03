package com.example.newsapp.core.designsystem.theme.values

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp


data class Colors(
    val primary: Color = Color(0xFF0094FF),
    val onPrimary: Color = Color(0xFFFFFFFF),

    val secondary: Color = Color(0xFFCCCCCC),

    val background: Color = Color(0xFFFFFFFF),
    val onBackground: Color = Color(0xFF000000),

    val error: Color = Color(0xFFFF1500),
    val white: Color = Color(0xFFFFFFFF),
    val black: Color = Color(0xFF000000),
    val grayText: Color = Color(0xFFCCCCCC),
)

data class Typography(
    val base: TextStyle,
    val baseBold: TextStyle,
)

data class Padding(
    val _4dp: Dp,
    val _8dp: Dp,
    val _12dp: Dp,
    val _16dp: Dp,
    val _24dp: Dp,
    val _32dp: Dp,
)

data class CornerShape(
    val rounded6dp: CornerBasedShape,
    val rounded8dp: CornerBasedShape,
    val rounded12dp: CornerBasedShape,
    val rounded16dp: CornerBasedShape,
)