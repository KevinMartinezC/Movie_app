package com.example.moviesvapp.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviesvapp.model.Resource
import com.example.moviesvapp.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: LoginViewModel, onLoginSuccess: (String) -> Unit) {
    val username = remember { mutableStateOf("") }
    val apiKey = remember { mutableStateOf("") }
    val loginStatus by viewModel.loginStatus.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text("Username") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = apiKey.value,
            onValueChange = { apiKey.value = it },
            label = { Text("API Key") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            viewModel.login(username = username.value, apiKey= apiKey.value) }) {
            Text("Login")
        }

        when (loginStatus) {
            is Resource.Loading -> Text("Logging in...")
            is Resource.Error -> {
                val errorStatus = loginStatus as Resource.Error
                val userFriendlyMessage = when (errorStatus.message) {
                    "Invalid API key" -> "Invalid API key. Please check your Password and try again."
                    else -> "An error occurred. Please try again later."
                }
                Text(
                    userFriendlyMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }


            is Resource.Success -> {
                val successStatus = loginStatus as Resource.Success
                LaunchedEffect(Unit) {
                    onLoginSuccess(successStatus.data)
                }
            }

            is Resource.Idle -> {
                Text(
                    "Please enter your username and password",

                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

