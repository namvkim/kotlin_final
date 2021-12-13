package com.example.jetnote

import ContentScreen
import DescriptionScreen
import FavoriteScreen
import HomeScreen
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.jetnote.routing.JetNotesRouter
import com.example.jetnote.routing.Screen
import com.example.jetnote.theme.JetNotesTheme
import com.example.jetnote.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetNotesTheme {
                MainActivityScreen(viewModel = MainViewModel())
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
@ExperimentalMaterialApi
private fun MainActivityScreen(viewModel: MainViewModel) {
    Surface {
        when (JetNotesRouter.currentScreen) {
            is Screen.Home -> HomeScreen(viewModel)
            is Screen.Description -> DescriptionScreen(viewModel)
            is Screen.Content -> ContentScreen(viewModel)
            is Screen.Favorite -> FavoriteScreen(viewModel)
        }
    }
}