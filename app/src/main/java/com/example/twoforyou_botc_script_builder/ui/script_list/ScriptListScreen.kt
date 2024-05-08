package com.example.twoforyou_botc_script_builder.ui.script_list

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
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
import com.example.twoforyou_botc_script_builder.navigation.Screen
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

    var isFromScriptWebsite by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        ) {
            items(state.scriptList) { script ->
//                Text(
//                    script.toString()
//                )

                ScriptItem(
                    script,
                    { viewModel.deleteScript(script) },
                    { navController.navigate("${Screen.ScriptDisplayScreen.route}/${script.id}") }
                )

                HorizontalDivider(color = Color.Black)
            }

        }
        Spacer(modifier = Modifier)



        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            FloatingActionButton(
                onClick = {
                    showDialog = true
                    isFromScriptWebsite = true
                },
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(
                    text = "스크립트툴에서 추가(.json파일)",
                    modifier = Modifier
                        .padding(4.dp)
                )

            }

            FloatingActionButton(
                onClick = {
                    showDialog = true
                    isFromScriptWebsite = false
                },
                modifier = Modifier
                    .weight(0.5f)
            ) {
                Text(
                    text = "Azure사이트에서 추가(.json파일)",
                    modifier = Modifier
                        .padding(4.dp)

                )

            }
        }


        if (showDialog and isFromScriptWebsite) {
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
                                viewModel.addScript(
                                    viewModel.jsonStringToScriptFromScriptWebsite(
                                        jsonText
                                    )
                                )
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

        if (showDialog and !isFromScriptWebsite) {
            var scriptTitle by remember { mutableStateOf("") }
            var scriptAuthor by remember { mutableStateOf("") }

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

                    TextField(
                        value = scriptTitle,
                        onValueChange = { updatedText ->
                            scriptTitle = updatedText
                        },
                        label = {
                            Text("시나리오 제목")
                        }
                    )

                    TextField(
                        value = scriptAuthor,
                        onValueChange = { updatedText ->
                            scriptAuthor = updatedText
                        },
                        label = {
                            Text("시나리오 작가명")
                        }
                    )
                    Button(
                        onClick = {
                            try {
                                viewModel.addScript(
                                    viewModel.jsonStringToScriptFromAzureWebsite(
                                        jsonString = jsonText,
                                        author = scriptAuthor,
                                        name = scriptTitle
                                    )
                                )
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


