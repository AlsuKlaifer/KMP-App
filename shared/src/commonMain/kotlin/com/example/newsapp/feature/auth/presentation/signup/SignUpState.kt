package com.example.newsapp.feature.auth.presentation.signup

import com.example.newsapp.feature.auth.presentation.signin.SignInAction


data class SignUpState(
    val username : String = "",
    val emailQuery: String = "",
    val passwordQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
)

sealed interface SignUpAction {
    data object NavigateBack : SignUpAction
    data object NavigateToProfile : SignUpAction
    data object ShowUserCreatedAccountToast : SignUpAction
}

sealed interface SignUpEvent {
    data class OnUsernameQueryChanged(val newUsername: String) : SignUpEvent
    data class OnEmailQueryChanged(val newEmail: String) : SignUpEvent
    data class OnPasswordQueryChanged(val newPassword: String) : SignUpEvent
    data object OnSignUpClicked : SignUpEvent
    data object OnBackClicked : SignUpEvent
}
