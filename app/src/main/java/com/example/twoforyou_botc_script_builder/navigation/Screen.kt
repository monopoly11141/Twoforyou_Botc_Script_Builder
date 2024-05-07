package com.example.twoforyou_botc_script_builder.navigation

sealed class Screen(val route: String) {

    object ScriptListScreen : Screen(route = "script_list_screen")

    object SelectCharacterScreen : Screen(route = "select_character_screen")

    object ScriptDisplayScreen : Screen(route = "script_display_screen")
}
