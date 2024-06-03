package com.example.newsapp.feature.auth.presentation.signin

import com.example.newsapp.core.firebase.FirebaseCrashlyticsBindings
import com.example.newsapp.core.firebase.ScreenEvent
import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.core.vm.BaseViewModel
import com.example.newsapp.feature.auth.domain.usecase.SignInUserUseCase
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInUserUseCase: SignInUserUseCase,
    firebaseCrashlyticsBindings: FirebaseCrashlyticsBindings?,
) : BaseViewModel<SignInState, SignInAction, SignInEvent>
    (SignInState()) {

    init {
        firebaseCrashlyticsBindings?.logScreenEvent(ScreenEvent.SIGN_IN)
    }

    override fun obtainEvent(event: SignInEvent) {
        when (event) {
            SignInEvent.OnBackClicked -> viewAction = SignInAction.NavigateBack
            is SignInEvent.OnEmailQueryChanged -> onEmailQueryChange(event.newEmail)
            is SignInEvent.OnPasswordQueryChanged -> onPasswordQueryChanged(event.newPassword)
            SignInEvent.OnSignInClicked -> onSignInClicked()
        }
    }

    private fun onEmailQueryChange(newQuery: String) {
        viewState = viewState.copy(emailQuery = newQuery)
    }

    private fun onPasswordQueryChanged(newQuery: String) {
        viewState = viewState.copy(passwordQuery = newQuery)
    }

    private fun onSignInClicked() {
        scope.launch {
            when (val result = signInUserUseCase(viewState.emailQuery, viewState.passwordQuery)) {
                is ResultWrapper.Failed -> {
                    viewState = viewState.copy(error = result.errorMessage)
                }

                is ResultWrapper.Success -> {
                    viewAction = SignInAction.NavigateToProfile
                    viewAction = null
                }
            }
        }
    }

}