package com.example.moviesvapp.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.moviesvapp.ui.components.MainScreen
import com.example.moviesvapp.ui.components.login.LoginScreen
import com.example.moviesvapp.ui.components.login.viewmodel.LoginViewModel
import com.example.moviesvapp.ui.theme.MyApplicationTheme


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

