package com.example.moviesvapp.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.moviesvapp.components.LoginScreen
import com.example.moviesvapp.components.MainScreen
import com.example.moviesvapp.model.getApiKey
import com.example.moviesvapp.model.saveApiKey
import com.example.moviesvapp.ui.theme.MyApplicationTheme
import com.example.moviesvapp.viewmodel.LoginViewModel


class MainActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                val loggedInApiKey = remember { mutableStateOf(sharedPreferences.getApiKey()) }

                if (loggedInApiKey.value == null) {
                    LoginScreen(viewModel) { apiKey ->
                        loggedInApiKey.value = apiKey
                        sharedPreferences.saveApiKey(apiKey)
                    }
                } else {
                    MainScreen()
                }
            }
        }
    }
}
