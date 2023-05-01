package com.example.moviesvapp.ui.components.login.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesvapp.data.utils.Resource
import com.example.moviesvapp.ui.components.login.LoginScreen

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val sampleLoginStatus = Resource.Idle

    LoginScreen(
        loginStatus = sampleLoginStatus,
        onClickedLogin = { _, _ -> },
        onLogin = { _, _ -> }
    )
}
