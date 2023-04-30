package com.example.moviesvapp.ui.components.logout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.moviesvapp.R

@Composable
fun LogoutDrawer(onLogout: () -> Unit, username: String?, lastLoginDate: String?) {
    ModalDrawerSheet {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_16dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(R.string.logo),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.height_16dp)))

                if (username != null && lastLoginDate != null) {
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                Icons.Default.AccountCircle,
                                contentDescription = stringResource(R.string.user_icon)
                            )
                        },
                        label = { Text(text = stringResource(R.string.user, username)) },
                        selected = false,
                        onClick = {},
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.height_8dp)))

                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                Icons.Default.DateRange,
                                contentDescription = stringResource(R.string.last_login_date_icon)
                            )
                        },
                        label = { Text(text = stringResource(R.string.date, lastLoginDate)) },
                        selected = false,
                        onClick = {},
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = onLogout,
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    content = { Text(stringResource(R.string.logout)) }
                )
            }
        }
    }
}