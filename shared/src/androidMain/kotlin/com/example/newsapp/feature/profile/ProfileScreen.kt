package com.example.newsapp.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.core.designsystem.theme.AppTheme
import com.example.newsapp.core.utils.rememberClick
import com.example.newsapp.core.widget.BaseImage
import com.example.newsapp.core.widget.BaseTextButton
import com.example.newsapp.core.widget.asBgColor
import com.example.newsapp.feature.profile.presentation.ProfileAction
import com.example.newsapp.feature.profile.presentation.ProfileEvent
import com.example.newsapp.feature.profile.presentation.ProfileState
import com.example.newsapp.feature.profile.presentation.ProfileViewModel
import com.valentinilk.shimmer.shimmer
import org.koin.androidx.compose.getViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = getViewModel(),
) {
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsState(initial = null)
    val consumer = rememberClick<ProfileEvent> { viewModel.obtainEvent(it) }

    ProfileActions(navController, action)

    ScreenContent(state, consumer)
}

@Composable
private fun ProfileActions(
    navController: NavController,
    action: ProfileAction?,
) {
    LaunchedEffect(key1 = action) {
        when (action) {
            ProfileAction.NavigateToSignInScreen -> navController.navigate("sign_in")
            null -> Unit
        }
    }
}


@Composable
private fun ScreenContent(
    state: ProfileState,
    consumer: (ProfileEvent) -> Unit,
) {
    if (state.user == null) {
        NotAuthorizedScreen(state = state, consumer)
    } else {
        UserDataScreen(state = state, consumer)
    }
}

@Composable
private fun NotAuthorizedScreen(
    state: ProfileState,
    consumer: (ProfileEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .asBgColor(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "You are not Authorized", style = AppTheme.typography.baseBold.copy(
                fontSize = 24.sp
            )
        )
        Spacer(modifier = Modifier.padding(8.dp))

        BaseTextButton(
            onClick = { consumer(ProfileEvent.OnSignInClicked) },
            textResId = R.string.sign_in
        )
    }
}

@Composable
private fun UserDataScreen(
    state: ProfileState,
    consumer: (ProfileEvent) -> Unit,
) {
    state.user?.let { user ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .asBgColor()
        ) {
            Spacer(modifier = Modifier.padding(32.dp))
            BaseImage(
                model = user.avatarUrl,
                requestBuilderTransform = { it.circleCrop() },
                loadingPlaceholder = {
                    Box(
                        modifier = Modifier
                            .shimmer()
                            .size(112.dp)
                            .background(
                                color = AppTheme.colors.onBackground,
                                shape = CircleShape
                            )
                    )
                },
                modifier = Modifier.size(112.dp)
            )
            Spacer(modifier = Modifier.padding(16.dp))

            Text(
                text = user.username, style = AppTheme.typography.baseBold.copy(
                    fontSize = 24.sp
                )
            )
            Text(
                text = user.email, style = AppTheme.typography.baseBold.copy(
                    fontSize = 18.sp,
                    color = AppTheme.colors.primary
                )
            )
        }
    }
}