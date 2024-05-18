package com.example.newsapp.core.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.newsapp.R

sealed class BottomNavigationScreen(
    @StringRes
    val label: Int? = null,
    val icon: ImageVector? = null,
    override val route: String,
) : Screen(route) {
    data object Home : BottomNavigationScreen(
        route = "home",
        label = R.string.news,
        icon = Icons.Filled.Home,
    )

    data object Categories : BottomNavigationScreen(
        route = "categories",
        label = R.string.categories,
        icon = Icons.AutoMirrored.Filled.List
    )

    data object Profile : BottomNavigationScreen(
        route = "profile",
        label = R.string.profile,
        icon = Icons.Filled.Person
    )
}
