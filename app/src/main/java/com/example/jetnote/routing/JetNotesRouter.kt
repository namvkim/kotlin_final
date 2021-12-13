package com.example.jetnote.routing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Class defining all possible screens in the app.
 */
sealed class Screen {
  object Home: Screen()
  object Description: Screen()
  object Content: Screen()
  object Favorite: Screen()
}

/**
 * Allows you to change the screen in the [MainActivity]
 *
 * Also keeps track of the current screen.
 */
object JetNotesRouter {
  var currentScreen: Screen by mutableStateOf(Screen.Home)

  fun navigateTo(destination: Screen) {
    currentScreen = destination
  }
}
