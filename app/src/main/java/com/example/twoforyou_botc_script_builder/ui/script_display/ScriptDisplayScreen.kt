package com.example.twoforyou_botc_script_builder.ui.script_display

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.twoforyou_botc_script_builder.data.model.Script
import com.example.twoforyou_botc_script_builder.ui.script_display.composable.CharacterItem

@Composable
fun ScriptDisplayScreen(
    navController: NavController,
    scriptId: Int,
    viewModel: ScriptDisplayViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    var script by remember { mutableStateOf(Script()) }

    var isTownsfolkSectionDetector by remember { mutableStateOf(true) }
    var isOutsiderSectionDetector by remember { mutableStateOf(true) }
    var isMinionSectionDetector by remember { mutableStateOf(true) }
    var isDemonSectionDetector by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = true) {
        script = viewModel.getScriptById(scriptId)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {

        items(script.charactersObjectList) { character ->
            CharacterItem(
                character = character
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Black
            )
        }
    }


}