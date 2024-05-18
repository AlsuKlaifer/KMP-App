package com.example.newsapp.feature.profile.presentation

import com.example.newsapp.core.CommonStateFlow
import com.example.newsapp.core.asCommonStateFlow
import com.example.newsapp.core.utils.ResultWrapper
import com.example.newsapp.core.vm.BaseViewModel
import com.example.newsapp.di.PlatformSDK
import com.example.newsapp.feature.auth.domain.usecase.GetCurrentUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : BaseViewModel() {

    private val getCurrentUserUseCase: GetCurrentUserUseCase by PlatformSDK.lazyInstance()

    private val _viewState = MutableStateFlow(ProfileState())

    protected var viewState: ProfileState
        get() = _viewState.value
        set(value) {
            _viewState.value = value
        }

    val viewStates: CommonStateFlow<ProfileState>
        get() = _viewState.asStateFlow().asCommonStateFlow()

    init {
        loadUser()
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