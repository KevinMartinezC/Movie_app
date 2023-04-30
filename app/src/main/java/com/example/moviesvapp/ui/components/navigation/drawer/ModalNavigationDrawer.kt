package com.example.moviesvapp.ui.components.navigation.drawer

import android.annotation.SuppressLint
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.moviesvapp.ui.components.logout.LogoutDrawer
import com.example.moviesvapp.ui.components.navigation.BottomBar
import com.example.moviesvapp.ui.components.navigation.BottomNavGraph
import com.example.moviesvapp.ui.components.topbar.AppTopBar
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreenWithDrawer(
    onLogout: () -> Unit,
    username: String?,
    lastLoginDate: String?,
    navController: NavHostController
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            LogoutDrawer(
                onLogout = onLogout,
                username = username,
                lastLoginDate = lastLoginDate
            )
        },
        modifier = Modifier,
        drawerState = drawerState,
        scrimColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.32f),
        content = {
            Scaffold(
                topBar = {
                    AppTopBar { scope.launch { drawerState.open() } }
                },
                bottomBar = { BottomBar (navController = navController) }
            ) {
                BottomNavGraph(navController = navController)
            }
        }
    )
}


