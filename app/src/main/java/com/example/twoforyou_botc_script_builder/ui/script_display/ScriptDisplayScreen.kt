package com.example.twoforyou_botc_script_builder.ui.script_display

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.twoforyou_botc_script_builder.data.model.Script

@Composable
fun ScriptDisplayScreen(
    navController: NavController,
    scriptId: Int,
    viewModel: ScriptDisplayViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    var script by remember { mutableStateOf(Script()) }

    LaunchedEffect(key1 = true) {
        script = viewModel.getScriptById(scriptId)
    }

    Text(text = script.toString())
}