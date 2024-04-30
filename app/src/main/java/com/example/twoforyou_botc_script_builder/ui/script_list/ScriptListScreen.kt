package com.example.twoforyou_botc_script_builder.ui.script_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ScriptListScreen(
    navController: NavController,
    viewModel: ScriptListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    var jsonText by remember { mutableStateOf("") }

    var showScript by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (showScript) {
            Text(
                text = state.displayingScript.toString()
            )
            Button(onClick = { showScript = false }) {
                Text("Go Back")
            }
        } else {
            TextField(
                value = jsonText,
                onValueChange = { updatedText ->
                    jsonText = updatedText
                },
                modifier = Modifier
                    .weight(0.9f)
            )

            Button(
                onClick = {
                    viewModel.jsonStringToString(jsonText)
                    showScript = true
                },
                modifier = Modifier
                    .weight(0.1f)
            ) {
                Text(
                    text = "Click to convert",
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }


    }

}