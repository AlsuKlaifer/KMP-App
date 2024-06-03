package com.example.newsapp.feature.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.core.designsystem.theme.AppTheme
import com.example.newsapp.core.utils.rememberClick
import com.example.newsapp.core.widget.BackTopBar
import com.example.newsapp.core.widget.BaseTextButton
import com.example.newsapp.feature.auth.presentation.signin.SignInAction
import com.example.newsapp.feature.auth.presentation.signin.SignInEvent
import com.example.newsapp.feature.auth.presentation.signin.SignInState
import com.example.newsapp.feature.auth.presentation.signin.SignInViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: SignInViewModel = getViewModel(),
) {
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsState(initial = null)
    val consumer = rememberClick<SignInEvent> { viewModel.obtainEvent(it) }

    SignInActions(navController, action)

    ScreenContent(state, consumer)
}

@Composable
private fun SignInActions(
    navController: NavController,
    action: SignInAction?,
) {
    LaunchedEffect(key1 = action) {
        when (action) {
            null -> Unit
            SignInAction.NavigateBack -> navController.navigateUp()
            SignInAction.NavigateToProfile -> navController.navigate("profile")
        }
    }
}


@Composable
private fun ScreenContent(
    state: SignInState,
    consumer: (SignInEvent) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxHeight(),
        containerColor = AppTheme.colors.background,
        topBar = {
            BackTopBar(title = stringResource(id = R.string.sign_in)) {
                consumer(SignInEvent.OnBackClicked)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.sign_in),
                    style = AppTheme.typography.baseBold.copy(
                        fontSize = 24.sp
                    )
                )

                Spacer(modifier = Modifier.padding(8.dp))

                OutlinedTextField(
                    value = state.emailQuery,
                    onValueChange = { string -> consumer(SignInEvent.OnEmailQueryChanged(string)) },
                    label = { Text("Email") },
                    shape = AppTheme.cornerShape.rounded16dp,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = state.passwordQuery,
                    onValueChange = { string -> consumer(SignInEvent.OnPasswordQueryChanged(string)) },
                    label = { Text("Password") },
                    shape = AppTheme.cornerShape.rounded16dp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.padding(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    BaseTextButton(
                        onClick = { consumer(SignInEvent.OnSignInClicked) },
                        textResId = R.string.sign_in
                    )
                }
            }
        }
    }
}