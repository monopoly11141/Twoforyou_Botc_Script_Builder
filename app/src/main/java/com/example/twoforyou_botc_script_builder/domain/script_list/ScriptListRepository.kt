package com.example.twoforyou_botc_script_builder.domain.script_list

import com.example.twoforyou_botc_script_builder.data.model.Script
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ScriptListRepository {

    val displayingScript: StateFlow<Script>

    fun jsonStringToScript(jsonString: String) : Script

    fun updateScript(script: Script)

    fun getAllScript() : Flow<List<Script>>

    suspend fun insertScript(script: Script)

    suspend fun deleteScript(script: Script)

    suspend fun deleteAllScript()
}