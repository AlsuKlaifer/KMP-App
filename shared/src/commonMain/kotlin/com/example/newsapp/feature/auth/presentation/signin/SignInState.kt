package com.example.newsapp.feature.auth.presentation.signin

data class SignInState(
    val emailQuery: String = "",
    val passwordQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
)

sealed interface SignInAction {
    data object NavigateBack : SignInAction
    data object NavigateToProfile : SignInAction
}

sealed interface SignInEvent {
    data class OnEmailQueryChanged(val newEmail: String) : SignInEvent
    data class OnPasswordQueryChanged(val newPassword: String) : SignInEvent
    data object OnSignInClicked : SignInEvent
    data object OnBackClicked : SignInEvent
}
