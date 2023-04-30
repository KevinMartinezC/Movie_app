package com.example.moviesvapp.ui.components.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesvapp.R
import com.example.moviesvapp.ui.theme.MyApplicationTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(onLogoutButtonClick: () -> Unit) {
    TopAppBar(
        title = { Text(stringResource(R.string.search)) },
        actions = {
            IconButton(onClick = onLogoutButtonClick) {
                Icon(Icons.Default.ExitToApp, contentDescription = (stringResource(R.string.logout)))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AppTopBarPreview() {
    MyApplicationTheme {
        AppTopBar(onLogoutButtonClick = { })
    }
}
