package com.example.twoforyou_botc_script_builder.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.twoforyou_botc_script_builder.navigation.Navigation
import com.example.twoforyou_botc_script_builder.ui.theme.Twoforyou_Botc_Script_BuilderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Twoforyou_Botc_Script_BuilderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //TROUBLE_BREWING_CHARACTERS.addTroubleBrewingCharactersToDatabase()
                    //BAD_MOON_RISING_CHARACTERS.addBadMoonRisingCharactersToDatabase()
                    //SECTS_AND_VIOLETS_CHARACTERS.addSectsAndVioletsCharactersToDatabase()
                    val navController = rememberNavController()
                    Navigation(navController)
                }
            }
        }
    }
}