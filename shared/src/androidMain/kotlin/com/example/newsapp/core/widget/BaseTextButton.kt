package com.example.newsapp.core.widget

import androidx.annotation.StringRes
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.newsapp.core.designsystem.theme.AppTheme

@Composable
fun BaseTextButton(
    onClick: () -> Unit,
    @StringRes
    textResId: Int,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.primary,
        )
    ) {
        Text(
            text = stringResource(id = textResId), style = AppTheme.typography.base.copy(
                fontSize = 20.sp
            )
        )
    }
}