package com.example.moviesvapp.model

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.moviesvapp.model.SharedPreferencesExtensionsKeys.API_KEY
import com.example.moviesvapp.model.SharedPreferencesExtensionsKeys.DATE_TIME_FORMAT
import com.example.moviesvapp.model.SharedPreferencesExtensionsKeys.LAST_LOGIN_DATE
import com.example.moviesvapp.model.SharedPreferencesExtensionsKeys.USER_NAME
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun SharedPreferences.saveUsername(username: String) {
    edit().putString(USER_NAME, username).apply()
}

fun SharedPreferences.getUsername(): String? {
    return getString(USER_NAME, null)
}

fun SharedPreferences.saveApiKey(apiKey: String) {
    edit().putString(API_KEY, apiKey).apply()
}

fun SharedPreferences.getApiKey(): String? {
    return getString(API_KEY, null)
}

fun SharedPreferences.clearUsername() {
    edit().remove(USER_NAME).apply()
}

fun SharedPreferences.clearApiKey() {
    edit().remove(API_KEY).apply()
}

fun SharedPreferences.saveLastLoginDate(date: String) {
    edit().putString(LAST_LOGIN_DATE, date).apply()
}

fun SharedPreferences.getLastLoginDate(): String? {
    return getString(LAST_LOGIN_DATE, null)
}

fun SharedPreferences.clearLastLoginDate() {
    edit().remove(LAST_LOGIN_DATE).apply()
}

@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentDateTime(): String {
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)
    return currentDateTime.format(formatter)
}

object SharedPreferencesExtensionsKeys{
    const val API_KEY ="API_KEY"
    const val  USER_NAME = "USERNAME"
    const val  LAST_LOGIN_DATE = "LAST_LOGIN_DATE"
    const val DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
}
