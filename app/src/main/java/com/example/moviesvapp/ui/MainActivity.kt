package com.example.moviesvapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.moviesvapp.model.clearApiKey
import com.example.moviesvapp.model.clearUsername
import com.example.moviesvapp.ui.components.login.LoginScreen
import com.example.moviesvapp.ui.components.MainScreen
import com.example.moviesvapp.model.getUsername
import com.example.moviesvapp.model.saveApiKey
import com.example.moviesvapp.model.saveUsername
import com.example.moviesvapp.ui.theme.MyApplicationTheme
import com.example.moviesvapp.ui.components.login.viewmodel.LoginViewModel


class MainActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                val loggedInUsername = remember { mutableStateOf(sharedPreferences.getUsername()) }
                Log.d("MainActivity", "Retrieved username from SharedPreferences: ${loggedInUsername.value}")

                if (loggedInUsername.value == null) {
                    LoginScreen(viewModel) { username, apiKey ->
                        loggedInUsername.value = username
                        sharedPreferences.saveUsername(username)
                        sharedPreferences.saveApiKey(apiKey)
                        Log.d("MainActivity", "Saved username and API key to SharedPreferences: $username, $apiKey")
                    }
                }else {
                    MainScreen(onLogout = {
                        loggedInUsername.value = null
                        sharedPreferences.clearUsername()
                        sharedPreferences.clearApiKey()
                        Log.d("MainActivity", "Cleared username and API key from SharedPreferences")
                    })
                }
            }
        }
    }
}
