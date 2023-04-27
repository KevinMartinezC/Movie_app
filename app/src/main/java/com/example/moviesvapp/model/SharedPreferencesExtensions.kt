package com.example.moviesvapp.model

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


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

fun SharedPreferences.clearUsername() {
    edit().remove("USERNAME").apply()
}

fun SharedPreferences.clearApiKey() {
    edit().remove("API_KEY").apply()
}

fun SharedPreferences.saveLastLoginDate(date: String) {
    edit().putString("LAST_LOGIN_DATE", date).apply()
}

fun SharedPreferences.getLastLoginDate(): String? {
    return getString("LAST_LOGIN_DATE", null)
}

fun SharedPreferences.clearLastLoginDate() {
    edit().remove("LAST_LOGIN_DATE").apply()
}

@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentDateTime(): String {
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return currentDateTime.format(formatter)
}
