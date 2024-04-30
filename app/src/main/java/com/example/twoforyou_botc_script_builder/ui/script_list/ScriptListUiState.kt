package com.example.twoforyou_botc_script_builder.ui.script_list

import com.example.twoforyou_botc_script_builder.data.model.Script

data class ScriptListUiState(
    val displayingScript : Script = Script(),
    val scriptList: List<Script> = emptyList(),
)