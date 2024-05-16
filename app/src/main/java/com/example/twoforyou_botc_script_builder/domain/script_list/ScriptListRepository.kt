package com.example.twoforyou_botc_script_builder.domain.script_list

import com.example.twoforyou_botc_script_builder.data.model.Character
import com.example.twoforyou_botc_script_builder.data.model.FabledCharacter
import com.example.twoforyou_botc_script_builder.data.model.Script
import com.example.twoforyou_botc_script_builder.data.model.helper.Script_General_Info
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ScriptListRepository {

    val displayingScript: StateFlow<Script>

    fun jsonStringToScriptFromScriptWebsite(jsonString: String) : Script

    fun jsonStringToScriptFromAzureWebsite(jsonString: String, scriptGeneralInfo: Script_General_Info) : Script

    fun updateScript(script: Script)

    fun getAllScript() : Flow<List<Script>>

    suspend fun insertScript(script: Script)

    suspend fun deleteScript(script: Script)

    suspend fun deleteAllScript()

    fun getCharacterByName(name: String) : Character?

    fun getFabledCharacterByName(name: String) : FabledCharacter?
}