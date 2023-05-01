package com.example.moviesvapp.ui.components.home.filter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.moviesvapp.R
import com.example.moviesvapp.data.utils.Filter


@Composable
fun FilterDropDownMenu(
    selectedFilter: Filter, onFilterSelected: (Filter) -> Unit
) {
    val filters = listOf(Filter.All, Filter.Movies, Filter.Series)
    var expanded by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.filter_label))
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.wight_8dp)))
        Box {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                filters.forEach { filter ->
                    DropdownMenuItem(onClick = {
                        onFilterSelected(filter)
                        expanded = false
                    }, text = {
                        Text(text = filter.toString())
                    })
                }
            }
            TextButton(
                onClick = { expanded = true }, contentPadding = PaddingValues(0.dp)
            ) {
                Text(text = selectedFilter.toString())
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = stringResource(R.string.filter_icon)
                )
            }
        }
    }
}

