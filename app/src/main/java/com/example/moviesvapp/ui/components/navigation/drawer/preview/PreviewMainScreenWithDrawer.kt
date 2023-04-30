package com.example.moviesvapp.ui.components.navigation.drawer.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.moviesvapp.ui.components.navigation.drawer.MainScreenWithDrawer

@Preview(showBackground = true, name = "Main Screen with Drawer")
@Composable
fun PreviewMainScreenWithDrawer() {
    val mockUsername = "JohnDoe"
    val mockLastLoginDate = "2023-04-28"

    MainScreenWithDrawer(
        onLogout = {},
        username = mockUsername,
        lastLoginDate = mockLastLoginDate,
        navController = rememberNavController()
    )
}