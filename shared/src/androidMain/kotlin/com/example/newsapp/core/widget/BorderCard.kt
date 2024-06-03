package com.example.newsapp.core.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsapp.core.designsystem.theme.AppTheme

@Composable
fun BorderCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .asBgColor(),
        shape = AppTheme.cornerShape.rounded12dp,
        border = BorderStroke(2.dp, AppTheme.colors.grayText),
        elevation = CardDefaults.cardElevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp, 0.dp),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.background),
        content = content,
        onClick = { onClick?.invoke() }
    )
}