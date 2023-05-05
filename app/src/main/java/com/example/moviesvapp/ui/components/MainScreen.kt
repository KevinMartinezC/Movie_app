package com.example.moviesvapp.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesvapp.ui.components.navigation.drawer.MainScreenWithDrawer
import com.example.moviesvapp.ui.theme.MyApplicationTheme

@Composable
fun MainScreen(
    onLogout: () -> Unit,
    username: String?,//Remove it to use empty string
    lastLoginDate: String?
) {

    val navController = rememberNavController()

    MyApplicationTheme {
        MainScreenWithDrawer(
            onLogout = onLogout,
            username = username,
            lastLoginDate = lastLoginDate,
            navController = navController
        )
    }
}


