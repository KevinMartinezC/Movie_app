package com.example.moviesvapp.model

import android.content.SharedPreferences

class PreferenceRepository(private val sharedPreferences: SharedPreferences) {

    fun getApiKey(): String? {
        return sharedPreferences.getApiKey()
    }

    fun saveApiKey(apiKey: String) {
        sharedPreferences.saveApiKey(apiKey)
    }
}
