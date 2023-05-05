package com.example.moviesvapp.ui.components.login.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesvapp.data.extensions.clearApiKey
import com.example.moviesvapp.data.extensions.clearLastLoginDate
import com.example.moviesvapp.data.extensions.clearUsername
import com.example.moviesvapp.data.extensions.getApiKey
import com.example.moviesvapp.data.extensions.getCurrentDateTime
import com.example.moviesvapp.data.extensions.getLastLoginDate
import com.example.moviesvapp.data.extensions.getUsername
import com.example.moviesvapp.data.extensions.saveApiKey
import com.example.moviesvapp.data.extensions.saveLastLoginDate
import com.example.moviesvapp.data.extensions.saveUsername
import com.example.moviesvapp.data.omdbapi.network.RetrofitInstance
import com.example.moviesvapp.data.utils.Resource
import com.example.moviesvapp.ui.components.home.viewmodel.MoviesViewModel.Companion.RESPONSE_STATE
import com.example.moviesvapp.ui.components.login.LoginUiState
import com.example.moviesvapp.ui.components.login.viewmodel.Constants.AN_ERROR_OCCURRED
import com.example.moviesvapp.ui.components.login.viewmodel.Constants.INVALID_API_KEY
import com.example.moviesvapp.ui.components.login.viewmodel.Constants.PREFS_NAME
import com.example.moviesvapp.ui.components.login.viewmodel.Constants.SEARCH_QUERY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class LoginViewModel(context: Context) : ViewModel() {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val _uiState = MutableStateFlow(
        LoginUiState(
            isLoggedIn = !sharedPreferences.getApiKey().isNullOrBlank(),
            userName = sharedPreferences.getUsername()
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
                val response = RetrofitInstance.omdbApi.searchMovies(apiKey, SEARCH_QUERY)
                if (response.response == RESPONSE_STATE) {
                    _uiState.update {
                        it.copy(
                            userName = username,
                            loginStatus = Resource.Success(Pair(username, apiKey))
                        )
                    }
                } else {
                    _uiState.update { it.copy(loginStatus = Resource.Error(INVALID_API_KEY)) }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        loginStatus = Resource.Error(
                            e.message ?: AN_ERROR_OCCURRED
                        )
                    )
                }
            }
        }
    }
}

object Constants {
    const val PREFS_NAME = "MyPrefs"
    const val SEARCH_QUERY = "marvel"
    const val INVALID_API_KEY = "Invalid API key"
    const val AN_ERROR_OCCURRED = "An error occurred"
    const val TAG_LOGIN_ERROR = "LoginError"
    const val EXCEPTION_CAUGHT_MESSAGE = "Exception caught: %s"

}