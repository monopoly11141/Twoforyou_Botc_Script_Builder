package com.example.twoforyou_botc_script_builder.domain.script_display

import com.example.twoforyou_botc_script_builder.data.model.Script
import kotlinx.coroutines.flow.StateFlow

interface ScriptDisplayRepository {
    val displayingScript: StateFlow<Script>

    suspend fun getScriptById(id: Int) : Script

    fun updateScript(script: Script)

}