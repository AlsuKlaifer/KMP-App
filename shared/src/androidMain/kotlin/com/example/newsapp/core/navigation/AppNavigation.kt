package com.example.newsapp.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.core.designsystem.theme.AppTheme


@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: BottomNavigationScreen = BottomNavigationScreen.Home,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = AppTheme.colors.background,
        bottomBar = {
            MainBottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        AppNavHost(
            navController = navController,
            startDestination = startDestination,
            innerPadding = paddingValues
        )
    }
}