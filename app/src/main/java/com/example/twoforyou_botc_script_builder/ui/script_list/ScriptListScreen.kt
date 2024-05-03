package com.example.twoforyou_botc_script_builder.ui.script_list

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.twoforyou_botc_script_builder.ui.script_list.composable.ScriptItem

@Composable
fun ScriptListScreen(
    navController: NavController,
    viewModel: ScriptListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    val context = LocalContext.current

    var jsonText by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(

        ) {
            items(state.scriptList) { script ->

                Text(
                    script.toString()
                )

                ScriptItem(
                    script,
                    { viewModel.deleteScript(script) }
                )

                HorizontalDivider(color = Color.Blue)
            }

        }
        Spacer(modifier = Modifier.weight(1f))




        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.End)
        ) {
            Row() {
                Text(
                    text = "스크립트 추가(.json파일)",
                    modifier = Modifier
                        .padding(4.dp)

                )

            }

        }

        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Column {
                    TextField(
                        value = jsonText,
                        onValueChange = { updatedText ->
                            jsonText = updatedText
                        },
                        label = {
                            Text("json을 복붙하세요")
                        }
                    )
                    Button(
                        onClick = {
                            try {
                                viewModel.addScript(viewModel.jsonStringToScript(jsonText))
                            } catch (e: Exception) {
                                Toast.makeText(context, "올바른 json을 입력하세요.", Toast.LENGTH_SHORT)
                                    .show()
                            } finally {
                                showDialog = false
                                jsonText = ""
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.End)
                    ) {
                        Text("Add Script")
                    }
                }

            }
        }
    }
}


