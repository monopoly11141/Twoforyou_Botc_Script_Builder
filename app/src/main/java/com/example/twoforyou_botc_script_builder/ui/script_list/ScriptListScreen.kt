package com.example.twoforyou_botc_script_builder.ui.script_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
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

    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(

        ) {
            items(state.scriptList) { script ->
                Text("$script")
                HorizontalDivider(color = Color.Blue)
            }

        }
        Spacer(modifier = Modifier.weight(1f))

        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Script")
        }
    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Column {
                TextField(
                    value = jsonText,
                    onValueChange = { updatedText ->
                        jsonText = updatedText
                    }
                )
                Button(onClick = {
                    viewModel.addScript(viewModel.jsonStringToScript(jsonText))
                    showDialog = false
                }) {
                    Text("Add Script")
                }
            }

        }
    }
}