package com.example.twoforyou_botc_script_builder.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.twoforyou_botc_script_builder.ui.script_list.ScriptListScreen
import com.example.twoforyou_botc_script_builder.ui.select_character.SelectCharacterScreen

@Composable
fun Navigation(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screen.ScriptListScreen.route
    ) {
        composable(route = Screen.ScriptListScreen.route) {
            ScriptListScreen(navController = navController)
        }

        composable(route = Screen.SelectCharacterScreen.route) {
            SelectCharacterScreen(navController = navController)
        }
    }
}