package com.example.twoforyou_botc_script_builder.data.script_list

import com.example.twoforyou_botc_script_builder.data.db.local.ScriptDao
import com.example.twoforyou_botc_script_builder.data.db.remote.FirebaseCharacterDatabase
import com.example.twoforyou_botc_script_builder.data.model.Script
import com.example.twoforyou_botc_script_builder.data.model.helper.Script_General_Info
import com.example.twoforyou_botc_script_builder.domain.script_list.ScriptListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ScriptListRepositoryImpl @Inject constructor(
    private val firebaseCharacterDatabase: FirebaseCharacterDatabase,
    private val scriptDao: ScriptDao
) : ScriptListRepository {

    private val _displayingScript = MutableStateFlow(Script())
    override val displayingScript: StateFlow<Script>
        get() = _displayingScript.asStateFlow()

    override fun jsonStringToScript(jsonString: String): Script {
        val modifiedJsonString = modifyJsonString(jsonString)

        val modifiedJsonStringArray = modifiedJsonString.split(",")
        val scriptGeneralInfo = Script_General_Info(
            modifiedJsonStringArray[0].split(":")[1].trim(),
            modifiedJsonStringArray[1].split(":")[1].trim(),
            modifiedJsonStringArray[2].split(":")[1].trim(),
        )

        val characterMutableList = mutableListOf<String>()

        for (i in 3 until modifiedJsonStringArray.size) {
            characterMutableList.add(modifiedJsonStringArray[i].trim())
        }

        val script = Script(0, scriptGeneralInfo, characterMutableList)

        return script

    }

    override fun updateScript(script: Script) {
        _displayingScript.value = script
    }

    override fun getAllScript(): Flow<List<Script>> {
        return scriptDao.getAllScript()
    }

    override suspend fun insertScript(script: Script) {
        scriptDao.insertScript(script)
    }

    override suspend fun deleteScript(script: Script) {
        scriptDao.deleteScript(script)
    }

    override suspend fun deleteAllScript() {
        scriptDao.deleteAllScript()
    }

    private fun modifyJsonString(jsonString: String): String {
        var rawStringValue = jsonString
        rawStringValue = rawStringValue.replace("[", "")
        rawStringValue = rawStringValue.replace("]", "")
        rawStringValue = rawStringValue.replace("{", "")
        rawStringValue = rawStringValue.replace("}", "")
        //rawStringValue = rawStringValue.replace(" ", "")
        rawStringValue = rawStringValue.replace("\"", "")

        return rawStringValue
    }


}