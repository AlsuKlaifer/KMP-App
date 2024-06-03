package com.example.newsapp.core.navigation


sealed class Screen(
    open val route: String,
) {
    data object Detail : Screen("detail/{$DETAIL_ARG}")
    data object SignIn : Screen("sign_in")
    data object SignUp : Screen("sign_up")

    companion object {
        const val DETAIL_ARG = "NEWS_TITLE"
    }
}