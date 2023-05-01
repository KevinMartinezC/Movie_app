package com.example.moviesvapp.ui.components.home.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesvapp.data.utils.Filter
import com.example.moviesvapp.ui.components.home.filter.FilterDropDownMenu
import com.example.moviesvapp.ui.theme.MyApplicationTheme

@Preview(showBackground = true)
@Composable
fun FilterDropDownMenuPreview() {
    MyApplicationTheme {
        FilterDropDownMenu(selectedFilter = Filter.All, onFilterSelected = { _ -> })
    }
}