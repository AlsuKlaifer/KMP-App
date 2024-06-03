package com.example.newsapp.feature.auth.presentation.signup

import com.example.newsapp.core.firebase.FirebaseCrashlyticsBindings
import com.example.newsapp.core.firebase.ScreenEvent
import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.core.vm.BaseViewModel
import com.example.newsapp.feature.auth.domain.usecase.SignUpUserUseCase
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signInUserUseCase: SignUpUserUseCase,
    firebaseCrashlyticsBindings: FirebaseCrashlyticsBindings?,
) : BaseViewModel<SignUpState, SignUpAction, SignUpEvent>
    (SignUpState()) {

    init {
        firebaseCrashlyticsBindings?.logScreenEvent(ScreenEvent.SIGN_UP)
    }

    override fun obtainEvent(event: SignUpEvent) {
        when (event) {
            SignUpEvent.OnBackClicked -> viewAction = SignUpAction.NavigateBack
            is SignUpEvent.OnEmailQueryChanged -> onEmailQueryChange(event.newEmail)
            is SignUpEvent.OnPasswordQueryChanged -> onPasswordQueryChanged(event.newPassword)
            is SignUpEvent.OnUsernameQueryChanged -> onUsernameQueryChange(event.newUsername)
            SignUpEvent.OnSignUpClicked -> onSignUpClicked()

        }
    }

    private fun onUsernameQueryChange(newQuery: String) {
        viewState = viewState.copy(username = newQuery)
    }

    private fun onEmailQueryChange(newQuery: String) {
        viewState = viewState.copy(emailQuery = newQuery)
    }

    private fun onPasswordQueryChanged(newQuery: String) {
        viewState = viewState.copy(passwordQuery = newQuery)
    }

    private fun onSignUpClicked() {
        scope.launch {
            when (val result = signInUserUseCase(
                viewState.username,
                viewState.emailQuery,
                viewState.passwordQuery,
            )) {
                is ResultWrapper.Failed -> {
                    viewState = viewState.copy(error = result.errorMessage)
                }

                is ResultWrapper.Success -> {
                    viewAction = SignUpAction.NavigateToProfile
                    viewAction = null

//                    viewAction = SignUpAction.ShowUserCreatedAccountToast
//                    viewAction = null
                }
            }
        }
    }


}