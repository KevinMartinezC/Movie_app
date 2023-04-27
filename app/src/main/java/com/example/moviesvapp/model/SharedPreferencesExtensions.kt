package com.example.moviesvapp.model

import android.content.SharedPreferences


fun SharedPreferences.saveUsername(username: String) {
    edit().putString("USERNAME", username).apply()
}

fun SharedPreferences.getUsername(): String? {
    return getString("USERNAME", null)
}

fun SharedPreferences.saveApiKey(apiKey: String) {
    edit().putString("API_KEY", apiKey).apply()
}

fun SharedPreferences.getApiKey(): String? {
    return getString("API_KEY", null)
}