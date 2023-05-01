package com.example.moviesvapp.ui.components.login

import com.example.moviesvapp.data.utils.Resource

data class LoginUiState(
    val isLoggedIn: Boolean = false,
    val userName: String? = null,
    val loginStatus: Resource<Pair<String, String>> = Resource.Idle
)

