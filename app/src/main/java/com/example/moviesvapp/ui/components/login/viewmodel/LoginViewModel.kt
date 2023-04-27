package com.example.moviesvapp.ui.components.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesvapp.model.Resource
import com.example.moviesvapp.model.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LoginViewModel : ViewModel() {
    private val loginStatusMutable = MutableStateFlow<Resource<Pair<String, String>>>(Resource.Idle)
    val loginStatus: StateFlow<Resource<Pair<String, String>>> = loginStatusMutable

    fun login(username: String, apiKey: String) {
        viewModelScope.launch {
            loginStatusMutable.value = Resource.Loading
            try {
                val response = RetrofitInstance.omdbApi.searchMovies(apiKey, "marvel")
                if (response.response == "True") {
                    loginStatusMutable.value = Resource.Success(Pair(username, apiKey))
                } else {
                    loginStatusMutable.value = Resource.Error("Invalid API key")
                }
            } catch (e: Exception) {
                loginStatusMutable.value = Resource.Error(e.message ?: "An error occurred")
                Log.d("LoginError", "Exception caught: ${e.message}")
            }
        }
    }
}
