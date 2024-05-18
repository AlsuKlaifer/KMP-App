package com.example.newsapp.feature.navigation


sealed class Screen(
    open val route: String,
) {
//    data object Home : Screen(route = "home")
//    data object Categories : Screen(route = "categories")
//    data object Profile : Screen(route = "profile")

    data object Detail : Screen("detail/{newsId}")
    data object SignIn : Screen("signIn")
}