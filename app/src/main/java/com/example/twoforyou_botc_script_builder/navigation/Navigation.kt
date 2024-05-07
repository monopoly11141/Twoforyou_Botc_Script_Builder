package com.example.twoforyou_botc_script_builder.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.twoforyou_botc_script_builder.ui.script_display.ScriptDisplayScreen
import com.example.twoforyou_botc_script_builder.ui.script_list.ScriptListScreen
import com.example.twoforyou_botc_script_builder.ui.select_character.SelectCharacterScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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

        composable(route = "${Screen.ScriptDisplayScreen.route}/{scriptId}",
            arguments = listOf(
                navArgument("scriptId") {
                    type = NavType.IntType
                }
            )
        ) {entry ->
            val scriptId = entry.arguments?.getInt("scriptId")!!
            ScriptDisplayScreen(
                navController = navController,
                scriptId = scriptId
            )
        }
    }
}