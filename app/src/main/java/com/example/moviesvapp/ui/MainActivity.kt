package com.example.moviesvapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviesvapp.components.MainScreen
import com.example.moviesvapp.components.MoviesScreen
import com.example.moviesvapp.model.MoviesViewModel
import com.example.musicplayercompose.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MainScreen()
            }
        }
        /*setContent {
            val viewModel: MoviesViewModel = viewModel()
            MoviesScreen(viewModel)
        }*/
    }
}

