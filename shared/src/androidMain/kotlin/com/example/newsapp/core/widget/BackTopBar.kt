package com.example.newsapp.core.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.core.designsystem.theme.AppTheme

@Composable
fun BackTopBar(
    title: String,
    onBackClick: () -> Unit = { },
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(AppTheme.colors.primary)
            .padding(horizontal = 8.dp)
            .height(56.dp)
            .fillMaxWidth()
    ) {

        IconButton(
            onClick = onBackClick,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = stringResource(
                    id = R.string.back
                ),
                tint = AppTheme.colors.white
            )
        }

        Text(
            text = title,
            style = AppTheme.typography.base.copy(
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = AppTheme.colors.white
            ),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}