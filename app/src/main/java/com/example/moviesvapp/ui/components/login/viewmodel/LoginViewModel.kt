package com.example.moviesvapp.ui.components.login.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesvapp.model.Resource
import com.example.moviesvapp.model.RetrofitInstance
import com.example.moviesvapp.model.clearApiKey
import com.example.moviesvapp.model.clearLastLoginDate
import com.example.moviesvapp.model.clearUsername
import com.example.moviesvapp.model.getApiKey
import com.example.moviesvapp.model.getCurrentDateTime
import com.example.moviesvapp.model.getLastLoginDate
import com.example.moviesvapp.model.getUsername
import com.example.moviesvapp.model.saveApiKey
import com.example.moviesvapp.model.saveLastLoginDate
import com.example.moviesvapp.model.saveUsername
import com.example.moviesvapp.ui.components.login.LoginUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class LoginViewModel(context: Context) : ViewModel() {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    private val _uiState = MutableStateFlow(
        LoginUiState(
            isLoggedIn = !sharedPreferences.getApiKey().isNullOrBlank(),
            userName = sharedPreferences.getUsername() // Add this line

        )
    )
    val uiState = _uiState.asStateFlow()


    fun getLastLoginDate(): String? {
        return sharedPreferences.getLastLoginDate()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveCredentials(username: String, apiKey: String) {
        sharedPreferences.saveUsername(username)
        sharedPreferences.saveApiKey(apiKey)
        sharedPreferences.saveLastLoginDate(getCurrentDateTime())
        _uiState.update {
            it.copy(
                isLoggedIn = true
            )
        }
    }

    fun clearData() {
        viewModelScope.launch(Dispatchers.IO) {
            clearCredentials()
            _uiState.update {
                it.copy(
                    isLoggedIn = false,
                    loginStatus = Resource.Idle

                )
            }
        }
    }

    private fun clearCredentials() {
        sharedPreferences.clearUsername()
        sharedPreferences.clearApiKey()
        sharedPreferences.clearLastLoginDate()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun login(username: String, apiKey: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(loginStatus = Resource.Loading) }
            try {
                val response = RetrofitInstance.omdbApi.searchMovies(apiKey, "marvel")
                if (response.response == "True") {
                    _uiState.update {
                        it.copy(
                            userName = username,
                            loginStatus = Resource.Success(Pair(username, apiKey))
                        )

                    }
                } else {
                    _uiState.update { it.copy(loginStatus = Resource.Error("Invalid API key")) }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        loginStatus = Resource.Error(
                            e.message ?: "An error occurred"
                        )
                    )
                }
                Log.d("LoginError", "Exception caught: ${e.message}")
            }
        }
    }
}
