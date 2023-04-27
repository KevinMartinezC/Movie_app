package com.example.moviesvapp.ui

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.moviesvapp.model.clearApiKey
import com.example.moviesvapp.model.clearLastLoginDate
import com.example.moviesvapp.model.clearUsername
import com.example.moviesvapp.model.getCurrentDateTime
import com.example.moviesvapp.model.getLastLoginDate
import com.example.moviesvapp.ui.components.login.LoginScreen
import com.example.moviesvapp.ui.components.MainScreen
import com.example.moviesvapp.model.getUsername
import com.example.moviesvapp.model.saveApiKey
import com.example.moviesvapp.model.saveLastLoginDate
import com.example.moviesvapp.model.saveUsername
import com.example.moviesvapp.ui.theme.MyApplicationTheme
import com.example.moviesvapp.ui.components.login.viewmodel.LoginViewModel


class MainActivity : ComponentActivity() {
    private lateinit var viewModel: LoginViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = LoginViewModel(this)
        setContent {
            MyApplicationTheme {
                val lastLoginDate = viewModel.getLastLoginDate()
                val uiState by viewModel.uiState.collectAsState()

                if (!uiState.isLoggedIn) {
                    LoginScreen(
                        loginStatus = uiState.loginStatus,
                        onClickedLogin = viewModel::login,
                        onLogin = viewModel::saveCredentials,
                    )
                } else {
                    MainScreen(
                        onLogout = {
                            viewModel.clearData()
                        },
                        username = uiState.userName,
                        lastLoginDate = lastLoginDate
                    )
                }
            }
        }
    }
}

