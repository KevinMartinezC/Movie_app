package com.example.moviesvapp.ui.components.login

import com.example.moviesvapp.model.Resource

data class LoginUiState(
    val isLoggedIn: Boolean = false,
    val userName: String = "",
    val loginStatus: Resource<Pair<String, String>> = Resource.Idle
)

