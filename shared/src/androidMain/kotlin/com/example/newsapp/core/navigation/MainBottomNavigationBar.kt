package com.example.newsapp.core.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.newsapp.core.designsystem.theme.AppTheme

@Composable
fun MainBottomNavigationBar(
    navController: NavHostController,
) {

    val bnvList = listOf(
        BottomNavigationScreen.Home,
        BottomNavigationScreen.Categories,
        BottomNavigationScreen.Profile
    )

    Column {
        HorizontalDivider(color = AppTheme.colors.secondary)
        NavigationBar(
            containerColor = AppTheme.colors.background
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            bnvList.forEach { navigationItem ->
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.route == navigationItem.route } == true,
                    label = {
                        Text(stringResource(id = navigationItem.label!!))
                    },
                    icon = {
                        Icon(imageVector = navigationItem.icon!!, contentDescription = null)
                    },
                    onClick = {
                        navController.navigate(navigationItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = NavigationBarItemColors(
                        selectedIconColor = AppTheme.colors.primary,
                        selectedTextColor = AppTheme.colors.primary,
                        selectedIndicatorColor = Color.Transparent,
                        unselectedIconColor = AppTheme.colors.secondary,
                        unselectedTextColor = AppTheme.colors.secondary,
                        disabledIconColor = AppTheme.colors.secondary,
                        disabledTextColor = AppTheme.colors.secondary,
                    )
                )
            }
        }
    }
}