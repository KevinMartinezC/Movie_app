package com.example.moviesvapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.moviesvapp.R
import com.example.moviesvapp.ui.components.navigation.BottomBar
import com.example.moviesvapp.ui.components.navigation.BottomNavGraph
import com.example.moviesvapp.ui.components.navigation.BottomNavItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(onLogout: () -> Unit) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    MaterialTheme {
        ModalNavigationDrawer(
            drawerContent = { LogoutDrawer(onLogout = onLogout) },
            modifier = Modifier,
            drawerState = drawerState,
            scrimColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.32f),
            content = {
                Scaffold(
                    topBar = {
                        AppTopBar { scope.launch { drawerState.open() } }
                    },
                    bottomBar = { BottomBar(navController = navController) }
                ) {
                    BottomNavGraph(navController = navController)
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(onLogoutButtonClick: () -> Unit) {
    TopAppBar(
        title = { Text("Search") },
        actions = {
            IconButton(onClick = onLogoutButtonClick) {
                Icon(Icons.Default.ExitToApp, contentDescription = "Logout")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogoutDrawer(onLogout: () -> Unit) {
    ModalDrawerSheet {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                NavigationDrawerItem(

                    icon = { Icon(Icons.Default.ExitToApp, contentDescription = "Logout") },
                    label = {
                        Button(
                            onClick = onLogout,
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                            content = { Text("Logout") }
                        )
                    },
                    selected = false,
                    onClick = onLogout,
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: BottomNavItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = stringResource(R.string.navigation_icon),
                tint = MaterialTheme.colorScheme.primary

            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        alwaysShowLabel = false,
        )
}
