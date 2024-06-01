package com.example.newsapp.feature.profile.presentation

import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.core.vm.BaseViewModel
import com.example.newsapp.di.PlatformSDK
import com.example.newsapp.feature.auth.domain.usecase.GetCurrentUserUseCase
import kotlinx.coroutines.launch

class ProfileViewModel : BaseViewModel<ProfileState, ProfileAction, ProfileEvent>(
    ProfileState()
) {

    private val getCurrentUserUseCase: GetCurrentUserUseCase by PlatformSDK.lazyInstance()

    init {
        loadUser()
    }

    override fun obtainEvent(event: ProfileEvent) {
        TODO("Not yet implemented")
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