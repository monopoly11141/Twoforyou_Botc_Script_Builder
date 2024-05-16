package com.example.twoforyou_botc_script_builder.data.script_list

import android.content.ContentValues.TAG
import android.util.Log
import com.example.twoforyou_botc_script_builder.data.db.local.ScriptDao
import com.example.twoforyou_botc_script_builder.data.db.remote.FirebaseCharacterDatabase
import com.example.twoforyou_botc_script_builder.data.model.Character
import com.example.twoforyou_botc_script_builder.data.model.FabledCharacter
import com.example.twoforyou_botc_script_builder.data.model.Script
import com.example.twoforyou_botc_script_builder.data.model.getEnglishName
import com.example.twoforyou_botc_script_builder.data.model.helper.Script_General_Info
import com.example.twoforyou_botc_script_builder.domain.script_list.ScriptListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ScriptListRepositoryImpl @Inject constructor(
    private val scriptDao: ScriptDao,
    private val firebaseCharacterDatabase: FirebaseCharacterDatabase
) : ScriptListRepository {

    private val _displayingScript = MutableStateFlow(Script())
    override val displayingScript: StateFlow<Script>
        get() = _displayingScript.asStateFlow()

    override fun jsonStringToScriptFromScriptWebsite(jsonString: String): Script {
        val modifiedJsonString = modifyJsonStringFromScriptWebsite(jsonString)

        val modifiedJsonStringArray = modifiedJsonString.split(",")

        Log.d(TAG, "jsonStringToScriptFromScriptWebsite array: ${modifiedJsonStringArray}")

        val scriptGeneralInfo = Script_General_Info(
            modifiedJsonStringArray[0].split(":")[1],
            modifiedJsonStringArray[1].split(":")[1],
            modifiedJsonStringArray[2].split(":")[1],
        )

        val characterMutableList = mutableListOf<String>()
        val charactersObjectList = mutableListOf<Character>()
        val fabledCharactersObjectList = mutableListOf<FabledCharacter>()

        for (i in 3 until modifiedJsonStringArray.size) {
            val trimmedString = modifiedJsonStringArray[i].replace("_", "").replace("-","").trim()
            Log.d(TAG, "jsonStringToScriptFromScriptWebsite: ${trimmedString}")
            characterMutableList.add(trimmedString)
            getCharacterByName(trimmedString)?.let {
                charactersObjectList.add(getCharacterByName(trimmedString)!!)
            } ?: fabledCharactersObjectList.add(getFabledCharacterByName(trimmedString)!!)
        }

        val script = Script(0, scriptGeneralInfo, characterMutableList, charactersObjectList, fabledCharactersObjectList)

        return script

    }

    override fun jsonStringToScriptFromAzureWebsite(jsonString: String, scriptGeneralInfo: Script_General_Info): Script {
        val modifiedJsonString = modifyJsonStringFromAzureWebsite(jsonString)
        val modifiedJsonStringArray = modifiedJsonString.split(", ")

        val characterMutableList = mutableListOf<String>()
        val charactersObjectList = mutableListOf<Character>()

        val fabledCharactersObjectList = mutableListOf<FabledCharacter>()

        for (element in modifiedJsonStringArray) {
            val elementTrimmed = element.replace("_", "").replace("-","").trim()
            Log.d("TAG", "jsonStringToScriptFromAzureWebsite: $elementTrimmed")
            characterMutableList.add(elementTrimmed)
            getCharacterByName(elementTrimmed)?.let {
                charactersObjectList.add(getCharacterByName(elementTrimmed)!!)
            } ?: fabledCharactersObjectList.add(getFabledCharacterByName(elementTrimmed)!!)
        }

        val script = Script(0, scriptGeneralInfo, characterMutableList, charactersObjectList, fabledCharactersObjectList)
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

    override fun getCharacterByName(name: String) : Character? {
        firebaseCharacterDatabase.characterList.value.forEach {
            Log.d(TAG, "getCharacterByName: ${it.getEnglishName()}")
            if (it.getEnglishName().equals(name.trim(), ignoreCase = true)) {
                return it
            }
        }
        return null
    }

    override fun getFabledCharacterByName(name: String): FabledCharacter? {
        firebaseCharacterDatabase.fabledCharacterList.value.forEach {
            if (it.getEnglishName().equals(name.trim(), ignoreCase = true)) {
                return it
            }
        }
        return null
    }

    private fun modifyJsonStringFromScriptWebsite(jsonString: String): String {
        var rawStringValue = jsonString
        rawStringValue = rawStringValue.replace("[", "")
        rawStringValue = rawStringValue.replace("]", "")
        rawStringValue = rawStringValue.replace("{", "")
        rawStringValue = rawStringValue.replace("}", "")
        rawStringValue = rawStringValue.replace("\"", "")

        return rawStringValue
    }

    private fun modifyJsonStringFromAzureWebsite(jsonString: String) : String {
        var rawStringValue = jsonString
        rawStringValue = rawStringValue.replace("[", "")
        rawStringValue = rawStringValue.replace("]", "")
        rawStringValue = rawStringValue.replace("{", "")
        rawStringValue = rawStringValue.replace("}", "")
        rawStringValue = rawStringValue.replace("\"", "")
        rawStringValue = rawStringValue.replace("id:", "")

        Log.d("TAG","rawStringValue : $rawStringValue")

        return rawStringValue
    }



}