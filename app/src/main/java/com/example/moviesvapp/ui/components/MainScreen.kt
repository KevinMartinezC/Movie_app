package com.example.moviesvapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
fun MainScreen(onLogout: () -> Unit, username: String?, lastLoginDate: String?) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    MaterialTheme {
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
fun LogoutDrawer(onLogout: () -> Unit, username: String?, lastLoginDate: String?) {
    ModalDrawerSheet {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (username != null && lastLoginDate != null) {
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                Icons.Default.AccountCircle,
                                contentDescription = "User icon"
                            )
                        },
                        label = { Text(text = "User: $username") },
                        selected = false,
                        onClick = {},
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                Icons.Default.DateRange,
                                contentDescription = "Last login date icon"
                            )
                        },
                        label = { Text(text = "Date: $lastLoginDate") },
                        selected = false,
                        onClick = {},
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = onLogout,
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    content = { Text("Logout") }
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
