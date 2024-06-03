package com.example.newsapp.feature.profile.presentation

import com.example.newsapp.core.firebase.FirebaseCrashlyticsBindings
import com.example.newsapp.core.firebase.ScreenEvent
import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.core.vm.BaseViewModel
import com.example.newsapp.feature.auth.domain.usecase.GetCurrentUserUseCase
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    firebaseCrashlyticsBindings: FirebaseCrashlyticsBindings?,
) : BaseViewModel<ProfileState, ProfileAction, ProfileEvent>(
    ProfileState()
) {

    init {
        loadUser()
        firebaseCrashlyticsBindings?.logScreenEvent(ScreenEvent.PROFILE)
    }

    override fun obtainEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.OnSignInClicked -> onSignInClicked()
            ProfileEvent.OnSignUpClicked -> onSignUpClicked()
        }
    }

    private fun onSignInClicked() {
        scope.launch {
            viewAction = ProfileAction.NavigateToSignInScreen
            viewAction = null
        }
    }

    private fun onSignUpClicked() {
        scope.launch {
            viewAction = ProfileAction.NavigateToSignUpScreen
            viewAction = null
        }
    }


    private fun loadUser() {
        scope.launch {
            viewState = viewState.copy(isLoading = true)

            viewState = when (val response = getCurrentUserUseCase()) {
                is ResultWrapper.Failed -> {
                    viewState.copy(isLoading = false, error = response.errorMessage)
                }

                is ResultWrapper.Success -> {
                    viewState.copy(user = response.data)
                }
            }
        }
    }

}