package com.example.moviesvapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesvapp.model.Resource
import com.example.moviesvapp.model.RetrofitInstance


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel : ViewModel() {
    private val _loginStatus = MutableStateFlow<Resource<String>>(Resource.Idle())
    val loginStatus: StateFlow<Resource<String>> = _loginStatus

    fun login(username: String, apiKey: String) {
        viewModelScope.launch {
            _loginStatus.value = Resource.Loading
            try {
                val response = RetrofitInstance.omdbApi.searchMovies(apiKey, "test")
                if (response.response == "True") {
                    _loginStatus.value = Resource.Success("Username: $username, API Key: $apiKey")
                } else {
                    _loginStatus.value = Resource.Error("Invalid API key")
                }
            } catch (e: HttpException) {
                if (e.code() == 401) {
                    _loginStatus.value = Resource.Error("Invalid API key")
                } else {
                    _loginStatus.value = Resource.Error("HTTP ${e.code()}")
                }
                Log.d("LoginError", "HTTP Exception caught: ${e.message}")
            } catch (e: Exception) {
                _loginStatus.value = Resource.Error(e.message ?: "An error occurred")
                Log.d("LoginError", "Exception caught: ${e.message}")
            }

        }
    }

}
