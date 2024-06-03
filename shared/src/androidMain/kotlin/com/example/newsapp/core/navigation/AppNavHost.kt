package com.example.newsapp.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsapp.feature.categories.CategoriesScreen
import com.example.newsapp.feature.detail.DetailScreen
import com.example.newsapp.feature.home.HomeScreen
import com.example.newsapp.feature.profile.ProfileScreen
import com.example.newsapp.feature.signin.SignInScreen
import com.example.newsapp.feature.signup.SignUpScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: BottomNavigationScreen,
    innerPadding: PaddingValues,
) {
    NavHost(
        navController,
        startDestination = startDestination.route,
        Modifier.padding(innerPadding),
    ) {
        composable(BottomNavigationScreen.Home.route) { HomeScreen(navController) }
        composable(BottomNavigationScreen.Categories.route) { CategoriesScreen(navController) }
        composable(BottomNavigationScreen.Profile.route) { ProfileScreen(navController) }

        composable(
            Screen.Detail.route,
            arguments = listOf(navArgument(Screen.DETAIL_ARG) { type = NavType.StringType })
        ) {
            val detailArg = it.arguments?.getString(Screen.DETAIL_ARG)
            detailArg?.let { DetailScreen(it, navController) }
        }

        composable(Screen.SignIn.route) { SignInScreen(navController) }

        composable(Screen.SignUp.route) { SignUpScreen(navController) }

    }
}