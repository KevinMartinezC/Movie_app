package com.example.moviesvapp.ui.components.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.moviesvapp.R
import com.example.moviesvapp.model.Resource

@Composable
fun LoginScreen(
    loginStatus: Resource<Pair<String, String>>,
    onClickedLogin: (String, String) -> Unit,
    onLogin: (String, String) -> Unit
) {

    val username = remember { mutableStateOf("") }
    val apiKey = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_16dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = stringResource(id = R.string.logo),
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.size_275))
                .padding(bottom = dimensionResource(id = R.dimen.padding_16dp))
        )

        TextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text(stringResource(R.string.username)) },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_8dp)))

        TextField(
            value = apiKey.value,
            onValueChange = { apiKey.value = it },
            label = { Text(stringResource(R.string.password)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_8dp)))

        Button(onClick = {
            onClickedLogin(username.value, apiKey.value)
        }) {
            Text(stringResource(R.string.login))
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.height_8dp)))

        when (loginStatus) {
            is Resource.Success -> {
                val (loggedInUsername, loggedInApiKey) = loginStatus.data
                onLogin(loggedInUsername, loggedInApiKey)
            }
            is Resource.Loading -> Text(stringResource(R.string.logging_in))
            is Resource.Error -> {
                Text(
                    stringResource(R.string.invalid__key_please_check_your_password),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            is Resource.Idle -> {
                Text(
                    stringResource(R.string.please_enter_your_username_and_password),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

