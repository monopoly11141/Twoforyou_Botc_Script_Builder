package com.example.twoforyou_botc_script_builder.domain.script_list

import com.example.twoforyou_botc_script_builder.data.model.Script
import kotlinx.coroutines.flow.StateFlow

interface ScriptListRepository {

    val displayingScript: StateFlow<Script>

    val scriptList: StateFlow<List<Script>>

    fun jsonStringToScript(jsonString: String) : Script

}